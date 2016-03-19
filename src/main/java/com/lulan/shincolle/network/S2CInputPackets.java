package com.lulan.shincolle.network;

import com.lulan.shincolle.utility.EntityHelper;
import com.lulan.shincolle.utility.LogHelper;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/** CLIENT TO SERVER: INPUT PACKETS (NO-GUI)
 *  server�ݰw��input packet���^��
 */
public class S2CInputPackets implements IMessage {
	
	private World world;
	private EntityPlayer player;
	private int type, worldID, entityID, value, value2;
	private int[] value3;
	
	//packet id
	public static final class PID {
		public static final byte CmdChOwner = 0;
	}
	
	
	public S2CInputPackets() {}  //�����n���ŰѼ�constructor, forge�~��ϥΦ�class

	/**type 0:(2 parms) command: change owner: 0:sender eid, 1:owner eid
	 * 
	 */
	public S2CInputPackets(int type, int...parms) {
        this.type = type;
        
        if(parms != null && parms.length > 0) {
        	this.value3 = parms.clone();
        }
    }
	
	//����packet��k, client side
	@Override
	public void fromBytes(ByteBuf buf) {	
		//get type and entityID
		this.type = buf.readByte();
	
		switch(type) {
		case PID.CmdChOwner:	//cmd: change owner packet
			{
				try {
					this.value = buf.readInt();  //int array length
					
					//get int array data
					if(this.value > 0) {
						this.value3 = new int[this.value];
						
						for(int i = 0; i < this.value; i++) {
							this.value3[i] = buf.readInt();
						}
						
						//ship change owner process
						EntityHelper.processShipChangeOwner(value3[0], value3[1]);
					}
				}
				catch(Exception e) {
					LogHelper.info("DEBUG : S2C input packet: change owner fail: "+e);
				}
			}
			break;
		}
	}

	//�o�Xpacket��k, server side
	@Override
	public void toBytes(ByteBuf buf) {
		switch(this.type) {
		case PID.CmdChOwner:	//cmd: change owner packet
			{
				buf.writeByte((byte)this.type);

				//send int array
				if(this.value3 != null) {
					//send array length
					buf.writeInt(this.value3.length);
					
					for(int geti : this.value3) {
						buf.writeInt(geti);
					}
				}
				//if array null
				else {
					buf.writeInt(0);
				}
			}
			break;
		}
	}
	
	//packet handler (inner class)
	public static class Handler implements IMessageHandler<S2CInputPackets, IMessage> {
		//����ʥ]�����debug�T��, client side
		@Override
		public IMessage onMessage(S2CInputPackets message, MessageContext ctx) {		
//			EntityPlayerMP player = ctx.getServerHandler().playerEntity;
//			LogHelper.info("DEBUG : get input packet");
			return null;
		}
    }
	

}



