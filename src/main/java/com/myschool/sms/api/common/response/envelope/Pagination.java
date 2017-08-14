package com.myschool.sms.api.common.response.envelope;


/**
 * created by lokesh on 28/12/16.
 */

public class Pagination {
    private int totalPages;
    private long numberOfElements;

    private int currentPage;
    private int currentSize;

    public Pagination(int totalPages, long numberOfElements, int currentPage, int currentSize) {
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.numberOfElements = numberOfElements;
        this.currentSize = currentSize;
    }

    public Pagination(int currentSize) {
        if (currentSize != 0) {
            this.totalPages = 1;
            this.currentPage = 1;
            this.numberOfElements = currentSize;
            this.currentSize = currentSize;
        } else if (currentSize == 0) {
            this.totalPages = 0;
            this.numberOfElements = 0;
            this.currentPage = 0;
            this.currentSize = 0;
        }
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(long numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }
}
