package application.cdms.data.structure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EfficientDataSet<T>{

	private final List<T> dataList;
	private final Map<T, Integer> dataMap;
	
	public EfficientDataSet(){
		dataList=new ArrayList<>();
		dataMap=new HashMap<>();
	}
	
	public void add(T t){
		dataList.add(t);
		int index = dataList.size()-1;
		dataMap.put(t,index);
	}
	public void remove(T t){
		Integer index = dataMap.get(t);
		if(index==null){
			return ;
		}
		int size = dataList.size();
		dataMap.remove(t);
		dataMap.put(dataList.get(size-1), index);
		Collections.swap(dataList, index, size-1);
		dataList.remove(size-1);
	}
	public void addOrReplace(T t){
		if(dataMap.containsKey(t)){
			replace(t);
			return ;
		}
		add(t);
	}
	public boolean isElementPresent(T t){
		if(dataMap.containsKey(t)){
			return true;
		}
		return false;
	}
	public void replace(T t){
		Integer index = dataMap.get(t);
		if(index==null){
			return ;
		}
		dataMap.put(t, index);
		dataList.set(index, t);
	}
	public List<T> getDataSet(){
		return dataList;
	}
	public int size(){
		return dataList.size();
	}
}
