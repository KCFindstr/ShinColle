package com.lulan.shincolle.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.lulan.shincolle.client.model.ModelCarrierWDemon;
import com.lulan.shincolle.reference.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCarrierWDemon extends RenderLiving {
	
	//�K���ɸ��|
	private static final ResourceLocation mobTextures = new ResourceLocation(Reference.TEXTURES_ENTITY+"EntityCarrierWDemon.png");
	private ModelCarrierWDemon model = null;
	
	public RenderCarrierWDemon(ModelCarrierWDemon par1, float par2) {
		super(par1, par2);
		this.model = par1;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return mobTextures;
	}
	
	//render item
	@Override
	protected void renderEquippedItems(EntityLivingBase host, float swing) {
//		//��_���`�C��
//		GL11.glColor3f(1.0F, 1.0F, 1.0F);
//		GL11.glPushMatrix();
//		GL11.glScalef(0.5F, 0.5F, 0.5F);
//		this.model.HeadS01.postRender(1.00F);
//		GL11.glScalef(0.5F, 0.5F, 0.5F);
//		GL11.glPopMatrix();
	}
	
	//�B�~�ק�K��, ���C��j�p���ʧ@
	@Override
	protected int shouldRenderPass(EntityLivingBase entity, int pass, float scale) {
//		if(pass != 0) {
			return -1;
//		}
//		else {
//			GL11.glPushMatrix();
			
//			GL11.glScalef(0.5F, 0.5F, 0.5F);
//			this.model.HeadS01.postRender(0.0125F);
//			GL11.glScalef(0.5F, 0.5F, 0.5F);
//			GL11.glPopMatrix();
//			return 1;
//		}
    }

}

