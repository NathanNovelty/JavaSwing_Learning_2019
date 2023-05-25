/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookmanagersql;

import java.io.Serializable;

/**
 *
 * @author NhanN
 */
public class BookDataDTO implements Serializable{
    private String bookCode;
    private String bookName;
    private String author;
    private String publisher;
    private int publishYear;
    private boolean isRent;

    public BookDataDTO() {
    }

    public BookDataDTO(String bookCode, String bookName, String author, String publisher, int publishYear, boolean isRent) {
        this.bookCode = bookCode;
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.isRent = isRent;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public boolean isIsRent() {
        return isRent;
    }

    public void setIsRent(boolean isRent) {
        this.isRent = isRent;
    }
    
    
}
