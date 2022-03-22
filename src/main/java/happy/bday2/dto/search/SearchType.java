package happy.bday2.dto.search;

public enum SearchType {
    NAME("이름"), MONTH("월");

    private final String searchName;

    SearchType(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchName() {
        return searchName;
    }

}
