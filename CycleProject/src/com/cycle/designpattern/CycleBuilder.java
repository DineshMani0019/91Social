package com.cycle.designpattern;

import com.cycle.entity.ChainAssembly;
import com.cycle.entity.Frame;
import com.cycle.entity.HandleBar;
import com.cycle.entity.Mudguard;
import com.cycle.entity.Seat;
import com.cycle.entity.Sticker;
import com.cycle.entity.Wheel;

/* 
 * Using Builder Design Pattern (Creational design pattern)
 * 
 * @author Dinesh Mani
 */
public class CycleBuilder {

	public Frame frameObj;
	public ChainAssembly chainAssemblyObj;
	public HandleBar handleBarObj;
	public Mudguard mudguardObj;
	public Seat seatingObj;
	public Sticker stickerObj;
	public Wheel wheelObj;

	public CycleBuilder(Frame frame, ChainAssembly chainAssembly, HandleBar handleBar, Mudguard mudguard, Seat seat,
			Sticker sticker, Wheel wheel) {
		this.frameObj = frame;
		this.chainAssemblyObj = chainAssembly;
		this.handleBarObj = handleBar;
		this.mudguardObj = mudguard;
		this.seatingObj = seat;
		this.stickerObj = sticker;
		this.wheelObj = wheel;
	}

	public Frame getFrameObj() {
		return frameObj;
	}

	public ChainAssembly getChainAssemblyObj() {
		return chainAssemblyObj;
	}

	public HandleBar getHandleBarObj() {
		return handleBarObj;
	}

	public Mudguard getMudguardObj() {
		return mudguardObj;
	}

	public Seat getSeatingObj() {
		return seatingObj;
	}

	public Sticker getStickerObj() {
		return stickerObj;
	}

	public Wheel getWheelObj() {
		return wheelObj;
	}

	public int getCyclePrice() {
		return (this.frameObj.getPrice() + this.chainAssemblyObj.getPrice() + this.handleBarObj.getPrice()
				+ this.mudguardObj.getPrice() + this.seatingObj.getPrice() + this.stickerObj.getPrice()
				+ this.wheelObj.getPrice());
	}

}
