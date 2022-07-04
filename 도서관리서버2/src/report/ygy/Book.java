package report.ygy;

import java.io.Serializable;
import java.util.Comparator;



public class Book  implements Serializable{
	private String title;
	private String date;
	private int price;
	private String writer;
	private int bookCount;
	private int num;
	
	
	
	public Book(String title, String date, int price, String writer, int bookCount) {
	//	super();
		this.title = title;
		this.date = date;
		this.price = price;
		this.writer = writer;
		this.bookCount = bookCount;
	}

	public Book() {
		
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getBookCount() {
		return bookCount;
	}
	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}
	
	@Override
	public String toString() {
		return title+"  "+date+"  "+price+"  "+writer+"  "+bookCount;
	}
}


class PriceComparater implements Comparator<Book>{
	@Override
	public int compare(Book o1, Book o2) {
		// TODO Auto-generated method stub
		if(o1.getPrice()<o2.getPrice()) return -1;
		if(o1.getPrice()>o2.getPrice()) return 1;
		else return 0;
	}
}

class PriceReverseComparator implements Comparator<Book>{

	@Override
	public int compare(Book o1, Book o2) {
		if(o1.getPrice()>o2.getPrice()) return -1;
		if(o1.getPrice()<o2.getPrice()) return 1;
		else return 0;
	}
}

class TitleComparator implements Comparator<Book>{

	@Override
	public int compare(Book o1, Book o2) {
		return o1.getTitle().compareTo(o2.getTitle());
	}
}


