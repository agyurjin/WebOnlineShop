package edu.webonlineshop.optimize;

import java.util.ArrayList;
import java.util.List;

import edu.webonlineshop.dal.entity.Point;
import edu.webonlineshop.dal.repository.impl.OrderDAO;

public class Optimize {

	private List<Point> pointsList;
	private OrderDAO orderDaoImpl;
	
	public Optimize(List<Point> pointsList, OrderDAO orderDaoImpl) {
		this.pointsList = pointsList;
		this.orderDaoImpl = orderDaoImpl;
	}

	public List<Point> optimiztaion() {
		List<Point> optimizedList = new ArrayList<Point>();

		int index = 0;
		double latitude = 0;
		double longitude = 0;
		double min = 10e8;
		double value = 0;

		if(pointsList == null) {
			return new ArrayList<Point>();
		}
		while(!pointsList.isEmpty()){
			for(int i = 0; i < pointsList.size(); i++) {
				value = Math.sqrt( Math.pow((pointsList.get(i).getLatitude() - latitude), 2) + 
						Math.pow((pointsList.get(i).getLongitude() - longitude), 2) );
				if(value <= min) {
					index = i;
					latitude = pointsList.get(i).getLatitude();
					longitude = pointsList.get(i).getLongitude();
					min = value;
				}
			}
			orderDaoImpl.updateOrderStatus(pointsList.get(index).getId(), "delivered");
			optimizedList.add(pointsList.get(index));
			pointsList.remove(index);
		}

		return optimizedList;
	}
}
