public class Equipment {
    private String code;
    private String name;
    private boolean available;

    public Equipment(String code, String name) {
        this.code = code;
        this.name = name;
        this.available = true;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void borrowEquipment() {
        available = false;
    }

    public void returnEquipment() {
        available = true;
    }

    public String getStatus() {
        if (available) {
            return "可借用";
        } else {
            return "已借出";
        }
    }

    @Override
    public String toString() {
        return "代碼：" + code
                + "，名稱：" + name
                + "，狀態：" + getStatus();
    }
}