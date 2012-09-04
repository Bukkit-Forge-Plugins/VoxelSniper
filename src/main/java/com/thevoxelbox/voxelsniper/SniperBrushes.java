package com.thevoxelbox.voxelsniper;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.entity.Player;

import com.thevoxelbox.voxelsniper.brush.AntiFreeze;
import com.thevoxelbox.voxelsniper.brush.Ball;
import com.thevoxelbox.voxelsniper.brush.Biome;
import com.thevoxelbox.voxelsniper.brush.BlendBall;
import com.thevoxelbox.voxelsniper.brush.BlendDisc;
import com.thevoxelbox.voxelsniper.brush.BlendVoxel;
import com.thevoxelbox.voxelsniper.brush.BlendVoxelDisc;
import com.thevoxelbox.voxelsniper.brush.Blob;
import com.thevoxelbox.voxelsniper.brush.BlockResetBrush;
import com.thevoxelbox.voxelsniper.brush.BlockResetBrushSurface;
import com.thevoxelbox.voxelsniper.brush.Brush;
import com.thevoxelbox.voxelsniper.brush.Canyon;
import com.thevoxelbox.voxelsniper.brush.CanyonSelection;
import com.thevoxelbox.voxelsniper.brush.CheckerVoxelDisc;
import com.thevoxelbox.voxelsniper.brush.CleanSnow;
import com.thevoxelbox.voxelsniper.brush.CloneStamp;
import com.thevoxelbox.voxelsniper.brush.Comet;
import com.thevoxelbox.voxelsniper.brush.CopyPasta;
import com.thevoxelbox.voxelsniper.brush.Cylinder;
import com.thevoxelbox.voxelsniper.brush.Disc;
import com.thevoxelbox.voxelsniper.brush.DiscFace;
import com.thevoxelbox.voxelsniper.brush.Dome;
import com.thevoxelbox.voxelsniper.brush.Drain;
import com.thevoxelbox.voxelsniper.brush.Ellipse;
import com.thevoxelbox.voxelsniper.brush.Entity;
import com.thevoxelbox.voxelsniper.brush.EntityRemoval;
import com.thevoxelbox.voxelsniper.brush.Eraser;
import com.thevoxelbox.voxelsniper.brush.Erode;
import com.thevoxelbox.voxelsniper.brush.Extrude;
import com.thevoxelbox.voxelsniper.brush.FillDown;
import com.thevoxelbox.voxelsniper.brush.FlatOcean;
import com.thevoxelbox.voxelsniper.brush.GenerateChunk;
import com.thevoxelbox.voxelsniper.brush.GenerateTree;
import com.thevoxelbox.voxelsniper.brush.HeatRay;
import com.thevoxelbox.voxelsniper.brush.IBrush;
import com.thevoxelbox.voxelsniper.brush.Jagged;
import com.thevoxelbox.voxelsniper.brush.Jockey;
import com.thevoxelbox.voxelsniper.brush.Line;
import com.thevoxelbox.voxelsniper.brush.Meteor;
import com.thevoxelbox.voxelsniper.brush.Move;
import com.thevoxelbox.voxelsniper.brush.Ocean;
import com.thevoxelbox.voxelsniper.brush.OceanSelection;
import com.thevoxelbox.voxelsniper.brush.Overlay;
import com.thevoxelbox.voxelsniper.brush.Painting;
import com.thevoxelbox.voxelsniper.brush.Pointless;
import com.thevoxelbox.voxelsniper.brush.PullTest;
import com.thevoxelbox.voxelsniper.brush.Punish;
import com.thevoxelbox.voxelsniper.brush.RandomErode;
import com.thevoxelbox.voxelsniper.brush.Ring;
import com.thevoxelbox.voxelsniper.brush.Rot2D;
import com.thevoxelbox.voxelsniper.brush.Rot2Dvert;
import com.thevoxelbox.voxelsniper.brush.Rot3D;
import com.thevoxelbox.voxelsniper.brush.Ruler;
import com.thevoxelbox.voxelsniper.brush.Scanner;
import com.thevoxelbox.voxelsniper.brush.Set;
import com.thevoxelbox.voxelsniper.brush.SetRedstoneFlip;
import com.thevoxelbox.voxelsniper.brush.ShellBall;
import com.thevoxelbox.voxelsniper.brush.ShellSet;
import com.thevoxelbox.voxelsniper.brush.ShellVoxel;
import com.thevoxelbox.voxelsniper.brush.Snipe;
import com.thevoxelbox.voxelsniper.brush.SnowCone;
import com.thevoxelbox.voxelsniper.brush.SpiralStaircase;
import com.thevoxelbox.voxelsniper.brush.SplatterBall;
import com.thevoxelbox.voxelsniper.brush.SplatterDisc;
import com.thevoxelbox.voxelsniper.brush.SplatterOverlay;
import com.thevoxelbox.voxelsniper.brush.SplatterVoxel;
import com.thevoxelbox.voxelsniper.brush.SplatterVoxelDisc;
import com.thevoxelbox.voxelsniper.brush.Spline;
import com.thevoxelbox.voxelsniper.brush.Stencil;
import com.thevoxelbox.voxelsniper.brush.StencilList;
import com.thevoxelbox.voxelsniper.brush.ThreePointCircle;
import com.thevoxelbox.voxelsniper.brush.TreeSnipe;
import com.thevoxelbox.voxelsniper.brush.Triangle;
import com.thevoxelbox.voxelsniper.brush.Underlay;
import com.thevoxelbox.voxelsniper.brush.VoltMeter;
import com.thevoxelbox.voxelsniper.brush.Voxel;
import com.thevoxelbox.voxelsniper.brush.VoxelDisc;
import com.thevoxelbox.voxelsniper.brush.VoxelDiscFace;
import com.thevoxelbox.voxelsniper.brush.WarpInStyle;

/**
 * 
 * @author Voxel
 */
public enum SniperBrushes {
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~przerwap~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \\
    SNIPE(Snipe.class, "s", "snipe", "voxelsniper.sniper.snipe"), // [ 1 ] \\
    DISC(Disc.class, "d", "disc", "voxelsniper.sniper.disc"), // [ 2 ] \\
    DISC_FACE(DiscFace.class, "df", "discface", "voxelsniper.sniper.discface"), // [ 3 ] \\
    BALL(Ball.class, "b", "ball", "voxelsniper.sniper.ball"), // [ 4 ] \\
    VOXEL(Voxel.class, "v", "voxel", "voxelsniper.sniper.voxel"), // [ 5 ] \\
    VOXEL_DISC(VoxelDisc.class, "vd", "voxeldisc", "voxelsniper.sniper.voxeldisc"), // [ 6 ] \\
    VOXEL_DISC_FACE(VoxelDiscFace.class, "vdf", "voxeldiscface", "voxelsniper.sniper.voxeldiscface"), // [ 7 ] \\
    ENTITY(Entity.class, "en", "entity", "voxelsniper.sniper.entity"), // [ 8 ] \\
    OCEAN(Ocean.class, "o", "ocean", "voxelsniper.sniper.ocean"), // [ 9 ] \\
    OCEAN_SELECTION(OceanSelection.class, "ocs", "oceanselection", "voxelsniper.sniper.oceanselection"), // [ 10 ] \\
    CLONE_STAMP(CloneStamp.class, "cs", "clonestamp", "voxelsniper.sniper.clonestamp"), // [ 11 ] \\
    ERODE(Erode.class, "e", "erode", "voxelsniper.sniper.erode"), // [ 12 ] \\
    SOFT_SELECT_TEST(PullTest.class, "pull", "pull", "voxelsniper.sniper.pull"), // [ 13 ] \\
    PAINTING(Painting.class, "paint", "painting", "voxelsniper.sniper.painting"), // [ 14 ] \\
    CANYON(Canyon.class, "ca", "canyon", "voxelsniper.sniper.canyon"), // [ 15 ] \\
    CANYON_SELECTION(CanyonSelection.class, "cas", "canyonselection", "voxelsniper.sniper.canyonselection"), // [ 16 ] \\
    TWO_D_ROTATION(Rot2D.class, "rot2", "rotation2D", "voxelsniper.sniper.rotation2D"), // [ 17 ] \\
    WARP_IN_STYLE(WarpInStyle.class, "world", "warpinstyle", "voxelsniper.sniper.warpinstyle"), // [ 18 ] \\
    FILL_DOWN(FillDown.class, "fd", "filldown", "voxelsniper.sniper.filldown"), // [ 19 ] \\
    SET(Set.class, "set", "set", "voxelsniper.sniper.set"), // [ 20 ] \\
    JOCKEY(Jockey.class, "jockey", "jockey", "voxelsniper.sniper.jockey"), // [ 21 ] \\
    ENTITY_REMOVAL(EntityRemoval.class, "er", "entityremoval", "voxelsniper.sniper.entityremoval"), // [ 22 ] \\
    RING(Ring.class, "ri", "ring", "voxelsniper.sniper.ring"), // [ 23 ] \\
    SHELL_SET(ShellSet.class, "shs", "shellset", "voxelsniper.sniper.shellset"), // [ 24 ] \\
    BIOME(Biome.class, "bio", "biome", "voxelsniper.sniper.biome"), // [ 25 ] \\

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~giltwist~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \\
    SPLATTER_DISC(SplatterDisc.class, "sd", "splatdisc", "voxelsniper.sniper.splatdisc"), // [ 1 ] \\
    SPLATTER_VOXEL_DISC(SplatterVoxelDisc.class, "svd", "splatvoxeldisc", "voxelsniper.sniper.splatvoxeldisc"), // [ 2 ] \\
    SPLATTER_BALL(SplatterBall.class, "sb", "splatball", "voxelsniper.sniper.splatball"), // [ 3 ] \\
    SPLATTER_VOXEL(SplatterVoxel.class, "sv", "splatvoxel", "voxelsniper.sniper.splatvoxel"), // [ 4 ] \\
    BLOB(Blob.class, "blob", "splatblob", "voxelsniper.sniper.splatblob"), // [ 5 ] \\
    SPIRAL_STAIRCASE(SpiralStaircase.class, "sstair", "spiralstaircase", "voxelsniper.sniper.spiralstaircase"), // [ 6 ] \\
    SPLATTER_OVERLAY(SplatterOverlay.class, "sover", "splatteroverlay", "voxelsniper.sniper.splatteroverlay"), // [ 7 ] \\
    BLEND_VOXEL_DISC(BlendVoxelDisc.class, "bvd", "blendvoxeldisc", "voxelsniper.sniper.blendvoxeldisc"), // [ 8 ] \\
    BLEND_VOXEL(BlendVoxel.class, "bv", "blendvoxel", "voxelsniper.sniper.blendvoxel"), // [ 9 ] \\
    BLEND_DISC(BlendDisc.class, "bd", "blenddisc", "voxelsniper.sniper.blenddisc"), // [ 10 ] \\
    BLEND_BALL(BlendBall.class, "bb", "blendball", "voxelsniper.sniper.blendball"), // [ 11 ] \\
    LINE(Line.class, "l", "line", "voxelsniper.sniper.line"), // [ 12 ] \\
    SNOW_CONE(SnowCone.class, "snow", "snowcone", "voxelsniper.sniper.snowcone"), // [ 13 ] \\
    SHELL_BALL(ShellBall.class, "shb", "shellball", "voxelsniper.sniper.shellball"), // [ 14 ] \\
    SHELL_VOXEL(ShellVoxel.class, "shv", "shellvoxel", "voxelsniper.sniper.shellvoxel"), // [ 15 ] \\
    RANDOM_ERODE(RandomErode.class, "re", "randomerode", "voxelsniper.sniper.randomerode"), // [ 16 ] \\
    METEOR(Meteor.class, "met", "meteor", "voxelsniper.sniper.meteor"), // [ 17 ] \\
    TRIANGLE(Triangle.class, "tri", "triangle", "voxelsniper.sniper.triangle"), // [ 19 ] \\
    ERASER(Eraser.class, "erase", "eraser", "voxelsniper.sniper.eraser"), // [ 20 ] \\
    COPYPASTA(CopyPasta.class, "cp", "copypasta", "voxelsniper.sniper.copypasta"), // [ 22 ] \\
    COMET(Comet.class, "com", "comet", "voxelsniper.sniper.comet"), // [ 23 ] \\
    JAGGED(Jagged.class, "j", "jagged", "voxelsniper.sniper.jagged"), // [ 24 ] \\
    THREEPOINTCIRCLE(ThreePointCircle.class, "tpc", "threepointcircle", "voxelsniper.sniper.threepointcircle"), // [ 25 ] \\

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Ghost8700~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \\
    GENERATE_TREE(GenerateTree.class, "gt", "generatetree", "voxelsniper.sniper.generatetree"), // [ 1 ] \\

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~DivineRage~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \\
    GENERATE_CHUNK(GenerateChunk.class, "gc", "generatechunk", "voxelsniper.sniper.generatechunk"), // [ 1 ] \\ // No documentation. Fucks up client-sided. Still works though.
    TREE_GENERATE(TreeSnipe.class, "t", "treesnipe", "voxelsniper.sniper.treesnipe"), // [ 2 ] \\
    POINTLESS(Pointless.class, "drlolol", "pointlessbrush", "voxelsniper.sniper.pointlessbrush"), // [ 4 ] \\
    SCANNER(Scanner.class, "sc", "scanner", "voxelsniper.sniper.scanner"), // [ 5 ] \\

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Gavjenks~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \\
    HEAT_RAY(HeatRay.class, "hr", "heatray", "voxelsniper.sniper.heatray"), // [ 1 ] \\
    OVERLAY(Overlay.class, "over", "overlay", "voxelsniper.sniper.overlay"), // [ 4 ] \\
    DOME(Dome.class, "dome", "domebrush", "voxelsniper.sniper.domebrush"), // [ 6 ] \\
    RULER(Ruler.class, "r", "ruler", "voxelsniper.sniper.ruler"), // [ 7 ] \\
    VOLT_METER(VoltMeter.class, "volt", "voltmeter", "voxelsniper.sniper.voltmeter"), // [ 8 ] \\
    DRAIN(Drain.class, "drain", "drain", "voxelsniper.sniper.drain"), // [ 10 ] \\
    THREE_D_ROTATION(Rot3D.class, "rot3", "rotation3D", "voxelsniper.sniper.rotation3D"), // [ 11 ] \\
    ANTI_FREEZE(AntiFreeze.class, "af", "antifreeze", "voxelsniper.sniper.antifreeze"), // [ 13 ] \\
    TWO_D_ROTATION_EXP(Rot2Dvert.class, "rot2v", "rotation2Dvertical", "voxelsniper.sniper.rotation2Dvertical"), // [ 21 ] \\
    STENCIL(Stencil.class, "st", "stencil", "voxelsniper.sniper.stencil"), // [ 23 ] \\
    STENCILLIST(StencilList.class, "sl", "stencillist", "voxelsniper.sniper.stencillist"), // [ 24 ] \\
    BLOCK_RESET_SURFACE(BlockResetBrushSurface.class, "brbs", "blockresetbrushsurface", "voxelsniper.sniper.blockresetbrushsurface"), // [25] \\
    FLAT_OCEAN(FlatOcean.class, "fo", "flatocean", "voxelsniper.sniper.flatocean"), // [ 26 ] \\
    
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~psanker~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \\
    ELLIPSE(Ellipse.class, "el", "ellipse", "voxelsniper.sniper.ellipse"), // [ 1 ] \\
    SPLINE(Spline.class, "sp", "spline", "voxelsniper.sniper.spline"), // [ 2 ] \\
    CLEAN_SNOW(CleanSnow.class, "cls", "cleansnow", "voxelsniper.sniper.cleansnow"), // [ 4 ] \\
    EXTRUDE(Extrude.class, "ex", "extrude", "voxelsniper.sniper.extrude"), // [ 5 ] \\

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Deamon~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \\
    SET_REDSTONE_FLIP(SetRedstoneFlip.class, "setrf", "setredstoneflip", "voxelsniper.sniper.setredstoneflip"), // [ 1 ] \\

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Jmck95~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \\
    UNDERLAY(Underlay.class, "under", "underlay", "voxelsniper.sniper.underlay"), // [ 1 ] \\

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Kavukamari~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \\
    CYLINDER(Cylinder.class, "c", "cylinder", "voxelsniper.sniper.cylinder"),

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Monofraps~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \\
    PUNISH(Punish.class, "p", "punish", "voxelsniper.sniper.punish"), // [ 1 ] \\

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~MikeMatrix~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \\
    MOVE(Move.class, "mv", "move", "voxelsniper.sniper.move"), // [1] \\
    BLOCK_RESET(BlockResetBrush.class, "brb", "blockresetbrush", "voxelsniper.sniper.blockresetbrush"), // [1] \\
    CHECKER_VOXEL_DISC(CheckerVoxelDisc.class, "cvd", "checkervoxeldisc", "voxelsniper.sniper.checkervoxeldisc"); // [1] \\

    private static final Map<String, SniperBrushes> BRUSHES;

    /**
     * @return HashMap<String, String>
     */
    public static HashMap<String, String> getBrushAlternates(Player player) {
        final HashMap<String, String> _temp = new HashMap<String, String>();

        for (final SniperBrushes _vb : SniperBrushes.BRUSHES.values()) {
        	if((player == null) || player.hasPermission(_vb.getPermission())) {
        		_temp.put(_vb.getLong(), _vb.getShort());
        	}
        }

        return _temp;
    }

    /**
     * @param name
     * @return Brush
     */
    public static IBrush getBrushInstance(final String name) {
        if (SniperBrushes.BRUSHES.containsKey(name)) {
            return SniperBrushes.BRUSHES.get(name).getBrush();
        } else {
            for (final SniperBrushes _vb : SniperBrushes.BRUSHES.values()) {
                if (_vb.getLong().equalsIgnoreCase(name)) {
                    return _vb.getBrush();
                }
            }
        }
        return null;
    }

    /**
     * @param brush
     * @return String
     */
    public static String getName(final Brush brush) {
        for (final SniperBrushes _vbs : SniperBrushes.BRUSHES.values()) {
            if (brush.getClass().getName().equals(_vbs.brush.getName())) {
                return _vbs.longName;
            }
        }
        return null;
    }

    /**
     * @return HashMap<String, Brush>
     */
    public static HashMap<String, Brush> getSniperBrushes(Player player) {
        final HashMap<String, Brush> _temp = new HashMap<String, Brush>();

        for (final Entry<String, SniperBrushes> _set : SniperBrushes.BRUSHES.entrySet()) {
        	if((player == null) || (player.hasPermission(_set.getValue().getPermission())))
            _temp.put(_set.getKey(), _set.getValue().getBrush());
        }

        return _temp;
    }

    /**
     * @param name
     * @return boolean
     */
    public static boolean hasBrush(final String name) {
        if (SniperBrushes.BRUSHES.containsKey(name)) {
            return true;
        } else {
            for (final SniperBrushes _vb : SniperBrushes.BRUSHES.values()) {
                if (_vb.getLong().equalsIgnoreCase(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Class<? extends Brush> brush;
    private String shortName;
    private String longName;
    private String permission;

	static {
        BRUSHES = new HashMap<String, SniperBrushes>();

        for (final SniperBrushes _vb : SniperBrushes.values()) {
            SniperBrushes.BRUSHES.put(_vb.getShort(), _vb);
        }
    }

    private SniperBrushes(final Class<? extends Brush> brush, final String shortName, final String longName, final String permissionNode) {
        this.brush = brush;
        this.shortName = shortName;
        this.longName = longName;
        this.permission = permissionNode;
    }

    private Brush getBrush() {
        Brush _brush;
        try {
            try {
                _brush = this.brush.getConstructor().newInstance();
                return _brush;
            } catch (final InstantiationException _ex) {
                Logger.getLogger(SniperBrushes.class.getName()).log(Level.SEVERE, null, _ex);
            } catch (final IllegalAccessException _ex) {
                Logger.getLogger(SniperBrushes.class.getName()).log(Level.SEVERE, null, _ex);
            } catch (final IllegalArgumentException _ex) {
                Logger.getLogger(SniperBrushes.class.getName()).log(Level.SEVERE, null, _ex);
            } catch (final InvocationTargetException _ex) {
                Logger.getLogger(SniperBrushes.class.getName()).log(Level.SEVERE, null, _ex);
            }
        } catch (final NoSuchMethodException _ex) {
            Logger.getLogger(SniperBrushes.class.getName()).log(Level.SEVERE, null, _ex);
        } catch (final SecurityException _ex) {
            Logger.getLogger(SniperBrushes.class.getName()).log(Level.SEVERE, null, _ex);
        }
        return null;
    }

    private String getLong() {
        return this.longName;
    }

    private String getShort() {
        return this.shortName;
    }
    
    public final String getPermission() {
		return permission;
	}
}
