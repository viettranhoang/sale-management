package vn.vit.common;

import java.util.ArrayList;

public enum CustomerType {
	MUA_LE, MUA_BUON, MUA_ONLINE;

	@Override
	public String toString() {
		switch (this) {
		case MUA_LE:
			return "Mua lẻ";
		case MUA_BUON:
			return "Mua buôn";
		case MUA_ONLINE:
			return "Mua qua mạng";
		}
		return super.toString();
	}	
}
