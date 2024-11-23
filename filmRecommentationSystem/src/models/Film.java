package models;

public class Film {
	
	private String title;
	
	public Film(String title) {
        this.title = title;
    }
	
	public String getTitle() {
		return title;
	}
	
	@Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' + '}';
    }
}
