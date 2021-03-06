package com.hollingsworth.arsnouveau.client.particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;

public class ParticleLineData implements IParticleFactory<ColorParticleTypeData> {
    private final IAnimatedSprite spriteSet;
    public static final String NAME = "line";

    public ParticleLineData(IAnimatedSprite sprite) {
        this.spriteSet = sprite;
    }

    @Override
    public Particle makeParticle(ColorParticleTypeData data, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
//            return new ParticleGlow(worldIn, x,y,z,xSpeed, ySpeed, zSpeed, worldIn.rand.nextInt(255), worldIn.rand.nextInt(255), worldIn.rand.nextInt(255), 1.0f, .25f, 36, this.spriteSet);
        return new ParticleLine(worldIn, x,y,z,xSpeed, ySpeed, zSpeed, data.color.getRed(), data.color.getGreen(), data.color.getBlue(), (float) ParticleUtil.inRange(0.05, 0.20), 20+worldIn.rand.nextInt(60),  this.spriteSet);

    }

    public static IParticleData createData(ParticleColor color) {
        return new ColorParticleTypeData(ModParticles.LINE_TYPE, color);
    }

}