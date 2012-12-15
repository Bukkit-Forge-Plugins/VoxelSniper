package com.thevoxelbox.voxelsniper;

import org.bukkit.Material;

import org.bukkit.material.MaterialData;

/**
 * Implementation of the VoxelGunsmith MaterialData interface.
 * 
 * @author MikeMatrix
 * 
 */
public class SniperMaterialData extends org.bukkit.material.MaterialData {

	
	static {
		System.out.println("");
	}
    private Material material;

    private byte data;

    /**
     * @param material
     * @param data
     */
    public SniperMaterialData(final Material material, final byte data) {
    	super(material, data);
        this.material = material;
        this.data = data;
    }

    @Override
    public final byte getData() {
        return super.getData();
    }

    public final Material getMaterial() {
    	
        return super.getItemType();
    }

    @Override
    public final void setData(final byte data) {
        super.setData(data);
    }

    public final void setMaterial(final Material material) {
       	this.material = material;
    }

}
