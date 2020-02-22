package com.facultative.web.command.pagination;

public class Parameter {
    private int cursorPosition;
    private int pageNumber;
    private int pagesCount;

    public Parameter(int cursorPosition, int pageNumber, int pagesCount) {
        this.cursorPosition = cursorPosition;
        this.pageNumber = pageNumber;
        this.pagesCount = pagesCount;
    }

    public int getCursorPosition() {
        return cursorPosition;
    }

    public void setCursorPosition(int cursorPosition) {
        this.cursorPosition = cursorPosition;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }
}
