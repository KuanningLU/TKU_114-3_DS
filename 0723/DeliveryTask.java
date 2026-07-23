public class DeliveryTask {
    private int taskNumber;
    private String customerName;
    private String address;

    public DeliveryTask(int taskNumber, String customerName, String address) {
        this.taskNumber = taskNumber;
        this.customerName = customerName;
        this.address = address;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "配送編號：" + taskNumber
                + "，客戶姓名：" + customerName
                + "，配送地址：" + address;
    }
}