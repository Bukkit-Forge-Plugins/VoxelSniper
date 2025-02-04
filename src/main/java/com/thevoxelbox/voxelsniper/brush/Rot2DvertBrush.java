package com.thevoxelbox.voxelsniper.brush;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;

import com.thevoxelbox.voxelsniper.Message;
import com.thevoxelbox.voxelsniper.SnipeData;
import com.thevoxelbox.voxelsniper.util.BlockWrapper;

/**
 * 
 * @author Gavjenks, hack job from the other 2d rotation brush blockPositionY piotr
 */
// The X Y and Z variable names in this file do NOT MAKE ANY SENSE. Do not attempt to actually figure out what on earth is going on here. Just go to the
// original 2d horizontal brush if you wish to make anything similar to this, and start there. I didn't bother renaming everything.
public class Rot2DvertBrush extends Brush {
    private static int timesUsed = 0;

    private int mode = 0;
    private int bSize;
    private int brushSize;
    private BlockWrapper[][][] snap;
    private double se;

    /**
     * 
     */
    public Rot2DvertBrush() {
        this.setName("2D Rotation");
    }

    private void getMatrix() {
        this.brushSize = (this.bSize * 2) + 1;

        this.snap = new BlockWrapper[this.brushSize][this.brushSize][this.brushSize];

        int _sx = this.getBlockPositionX() - this.bSize;
        int _sy = this.getBlockPositionY() - this.bSize;
        int _sz = this.getBlockPositionZ() - this.bSize;

        for (int _x = 0; _x < this.snap.length; _x++) {
            _sz = this.getBlockPositionZ() - this.bSize;

            for (int _z = 0; _z < this.snap.length; _z++) {
                _sy = this.getBlockPositionY() - this.bSize;

                for (int _y = 0; _y < this.snap.length; _y++) {
                    final Block _b = this.clampY(_sx, _sy, _sz); // why is this not sx + x, sy + y sz + z?
                    this.snap[_x][_y][_z] = new BlockWrapper(_b);
                    _b.setTypeId(0);
                    _sy++;
                }

                _sz++;
            }
            _sx++;
        }
    }

    private void rotate(final SnipeData v) {
        final double _bPow = Math.pow(this.bSize + 0.5, 2);
        final double _cos = Math.cos(this.se);
        final double _sin = Math.sin(this.se);
        final boolean[][] _doNotFill = new boolean[this.snap.length][this.snap.length];
        // I put y in the inside loop, since it doesn't have any power functions, should be much faster.
        // Also, new array keeps track of which x and z coords are being assigned in the rotated space so that we can
        // do a targeted filling of only those columns later that were left out.

        for (int _x = 0; _x < this.snap.length; _x++) {
            final int _xx = _x - this.bSize;
            final double _xPow = Math.pow(_xx, 2);

            for (int _z = 0; _z < this.snap.length; _z++) {
                final int _zz = _z - this.bSize;

                if (_xPow + Math.pow(_zz, 2) <= _bPow) {
                    final double _newX = (_xx * _cos) - (_zz * _sin);
                    final double _newZ = (_xx * _sin) + (_zz * _cos);

                    _doNotFill[(int) _newX + this.bSize][(int) _newZ + this.bSize] = true;

                    for (int _y = 0; _y < this.snap.length; _y++) {
                        final int _yy = _y - this.bSize;

                        final BlockWrapper _vb = this.snap[_y][_x][_z];
                        if (_vb.getId() == 0) {
                            continue;
                        }
                        this.setBlockIdAndDataAt(_vb.getId(), _vb.getData(), this.getBlockPositionX() + _yy, this.getBlockPositionY() + (int) _newX,
                                this.getBlockPositionZ() + (int) _newZ);
                    }
                }
            }
        }

        for (int _x = 0; _x < this.snap.length; _x++) {
            final double _xPow = Math.pow(_x - this.bSize, 2);
            final int _fx = _x + this.getBlockPositionX() - this.bSize;

            for (int _z = 0; _z < this.snap.length; _z++) {
                if (_xPow + Math.pow(_z - this.bSize, 2) <= _bPow) {
                    final int _fz = _z + this.getBlockPositionZ() - this.bSize;

                    if (!_doNotFill[_x][_z]) {
                        // smart fill stuff
                        for (int _y = 0; _y < this.snap.length; _y++) {
                            final int _fy = _y + this.getBlockPositionY() - this.bSize;

                            final int _a = this.getBlockIdAt(_fy, _fx + 1, _fz);
                            final byte _aData = this.getBlockDataAt(_fy, _fx + 1, _fz);
                            final int _d = this.getBlockIdAt(_fy, _fx - 1, _fz);
                            final byte _dData = this.getBlockDataAt(_fy, _fx - 1, _fz);
                            final int _c = this.getBlockIdAt(_fy, _fx, _fz + 1);
                            final byte _cData = this.getBlockDataAt(_fy, _fx, _fz + 1);
                            final int _b = this.getBlockIdAt(_fy, _fx, _fz - 1);
                            final byte _bData = this.getBlockDataAt(_fy, _fx, _fz - 1);

                            int _winner;
                            byte _winnerData;

                            if (_a == _b || _a == _c || _a == _d) { // I figure that since we are already narrowing it down to ONLY the holes left behind, it
                                                                    // should
                                // be fine to do all 5 checks needed to be legit about it.
                                _winner = _a;
                                _winnerData = _aData;
                            } else if (_b == _d || _c == _d) {
                                _winner = _d;
                                _winnerData = _dData;
                            } else {
                                _winner = _b; // blockPositionY making this default, it will also automatically cover situations where B = C;
                                _winnerData = _bData;
                            }

                            this.setBlockIdAndDataAt(_winner, _winnerData, _fy, _fx, _fz);
                        }
                    }
                }
            }
        }
    }

    @Override
    protected final void arrow(final SnipeData v) {
        this.bSize = v.getBrushSize();

        switch (this.mode) {
        case 0:
            this.getMatrix();
            this.rotate(v);
            break;

        default:
            v.owner().getPlayer().sendMessage(ChatColor.RED + "Something went wrong.");
            break;
        }
    }

    @Override
    protected final void powder(final SnipeData v) {
        this.bSize = v.getBrushSize();

        switch (this.mode) {
        case 0:
            this.getMatrix();
            this.rotate(v);
            break;

        default:
            v.owner().getPlayer().sendMessage(ChatColor.RED + "Something went wrong.");
            break;
        }
    }

    @Override
    public final void info(final Message vm) {
        vm.brushName(this.getName());
    }

    @Override
    public final void parameters(final String[] par, final SnipeData v) {
        try {
            this.se = Math.toRadians(Double.parseDouble(par[1]));
            v.sendMessage(ChatColor.GREEN + "Angle set to " + this.se);
        } catch (Exception _ex) {
            v.sendMessage("Exception while parsing parameter: " + par[1]);
            Bukkit.getLogger().severe(_ex.getMessage());
        }
    }

    @Override
    public final int getTimesUsed() {
        return Rot2DvertBrush.timesUsed;
    }

    @Override
    public final void setTimesUsed(final int tUsed) {
        Rot2DvertBrush.timesUsed = tUsed;
    }
}
