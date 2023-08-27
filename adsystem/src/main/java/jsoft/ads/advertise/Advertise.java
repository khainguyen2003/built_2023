package jsoft.ads.advertise;

import jsoft.object.AdvertiseObject;

public interface Advertise {
	public boolean addSection(AdvertiseObject item);
	public boolean editSection(AdvertiseObject item);
	public boolean delSection(AdvertiseObject item);
	
	//Lấy 1 bản ghi qua id
	public Object getAdvertise(int id);
	
	//Lấy bản nhiều ghi
	public Object getAdvertise(AdvertiseObject similar, int at, byte total);
}
