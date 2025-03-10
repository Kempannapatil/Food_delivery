package com.tapfood.dao;

import java.util.List;

import com.tapfood.model.Menu;

public interface MenuDAO {

  List<Menu>	getAll(int restaurantid);
  
  
  int deleteMenu(int menuid);
  
  int updateMenu(Menu menu);
  
  int addMenu(Menu menu);
}
