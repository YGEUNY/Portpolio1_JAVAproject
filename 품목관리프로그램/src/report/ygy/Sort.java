package report.ygy;

import java.util.Comparator;

class Sort {

}


class modelCompare implements Comparator<Product>{

	public int compare(Product p1, Product p2) {	
		return p1.getModel().compareTo(p2.getModel());
	}	
}

class modelReverseCompare implements Comparator<Product>{

	public int compare(Product p1, Product p2) {	
		return p2.getModel().compareTo(p1.getModel());
	}
}

class priceCompare implements Comparator<Product>{
	public int compare(Product p1, Product p2) {
		if(p1.getPrice()<p2.getPrice())	return -1;
		else if(p1.getPrice()>p2.getPrice())	return 1;
		else	return 0;
	}
}

class priceReverseCompare implements Comparator<Product>{
	public int compare(Product p1, Product p2) {
		if(p1.getPrice()>p2.getPrice())	return -1;
		else if(p1.getPrice()<p2.getPrice())	return 1;
		else	return 0;
	}
}

class stockCompare implements Comparator<Product>{
	public int compare(Product p1, Product p2) {
		if(p1.getNumOfStock()<p2.getNumOfStock())	return -1;
		else if(p1.getNumOfStock()>p2.getNumOfStock())	return 1;
		else return 0;
	}
}