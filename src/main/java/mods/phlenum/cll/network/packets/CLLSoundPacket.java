package mods.phlenum.cll.network.packets;

import java.io.IOException;

import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

public class CLLSoundPacket extends CLLPacket {
	
	private double soundX;
	private double soundY;
	private double soundZ;
	private char soundID;
	private float soundVolume;
	private float soundPitch;
	
	public CLLSoundPacket(){}
	
	public CLLSoundPacket(BlockPos pos, char id, float volume, float pitch){
		soundX = pos.getX();
		soundY = pos.getY();
		soundZ = pos.getZ();
		soundID = id;
		soundVolume = volume;
		soundPitch = pitch;
	}

	@Override
	public void writeDataTo(ByteBufOutputStream buffer) throws IOException {
		buffer.writeDouble(soundX);
		buffer.writeDouble(soundY);
		buffer.writeDouble(soundZ);
		buffer.writeChar(soundID);
		buffer.writeFloat(soundVolume);
		buffer.writeFloat(soundPitch);
	}

	@Override
	public void readDataFrom(ByteBufInputStream buffer) throws IOException {
		soundX = buffer.readDouble();
		soundY = buffer.readDouble();
		soundZ = buffer.readDouble();
		soundID = buffer.readChar();
		soundVolume = buffer.readFloat();
		soundPitch = buffer.readFloat();
	}

	@Override
	public void handleClientSide(EntityPlayer playerSP) {
		//final CLLSounds whatSound = CLLSounds.values()[soundID];
		//playerSP.world.playSound(playerSP, soundX, soundY, soundZ, whatSound.getSoundEvent(), whatSound.getSoundCategory(), soundVolume, soundPitch);
	}

	@Override
	public void handleServerSide(EntityPlayer playerMP) {
		
	}

	
}
