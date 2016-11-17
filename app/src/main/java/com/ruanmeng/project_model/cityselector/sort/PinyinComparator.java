package com.ruanmeng.project_model.cityselector.sort;

import com.ruanmeng.project_model.cityselector.CityModel;

import java.util.Comparator;

public class PinyinComparator implements Comparator<CityModel.CityInfo> {

	public int compare(CityModel.CityInfo o1, CityModel.CityInfo o2) {
		if (o1.getSortLetters().equals("@")
				|| o2.getSortLetters().equals("#")) {
			return -1;
		} else if (o1.getSortLetters().equals("#")
				|| o2.getSortLetters().equals("@")) {
			return 1;
		} else {
			return o1.getSortLetters().compareTo(o2.getSortLetters());
		}
	}

}
