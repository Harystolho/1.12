package harystolho.uberminer.booklet;

import java.util.List;

public class BookletChapter {

	private String name;
	private List<BookletPage> pages;
	
	public BookletChapter(String name) {
		this.name = name;
	}
	
	public void addPage(int index, BookletItem list) {
		pages.add(new BookletPage(list));
	}
	
}
