package models;

public class Reimbursments {
    private Integer reimb_id;
    private Integer amount;
    private String date_submitted;
    private String date_resolved;
    private String description;
    private String receipt;
    private Integer author;
    private Integer resolver;
    private Integer reimb_stat_id;
    private Integer reimb_stat_type;


    public Reimbursments(){}
    public Reimbursments(Integer reimb_id,Integer amount,String date_submitted,String date_resolved,
                         String description,String receipt,Integer author,Integer resolver,Integer reimb_stat_id,Integer reimb_stat_type)
    {
        this.reimb_id = reimb_id;
        this.amount = amount;
        this.date_submitted = date_submitted;
        this.date_resolved = date_resolved;
        this.description = description;
        this.receipt = receipt;
        this.author = author;
        this.resolver = resolver;
        this.reimb_stat_id = reimb_stat_id;
        this.reimb_stat_type = reimb_stat_type;
    }

    public Integer getReimb_id() {
        return reimb_id;
    }

    public void setReimb_id(Integer reimb_id) {
        this.reimb_id = reimb_id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDate_submitted() {
        return date_submitted;
    }

    public void setDate_submitted(String date_submitted) {
        this.date_submitted = date_submitted;
    }

    public String getDate_resolved() {
        return date_resolved;
    }

    public void setDate_resolved(String date_resolved) {
        this.date_resolved = date_resolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public Integer getResolver() {
        return resolver;
    }

    public void setResolver(Integer resolver) {
        this.resolver = resolver;
    }

    public Integer getReimb_stat_id() {
        return reimb_stat_id;
    }

    public void setReimb_stat_id(Integer reimb_stat_id) {
        this.reimb_stat_id = reimb_stat_id;
    }

    public Integer getReimb_stat_type() {
        return reimb_stat_type;
    }

    public void setReimb_stat_type(Integer reimb_stat_type) {
        this.reimb_stat_type = reimb_stat_type;
    }

    @Override
    public String toString() {
        return "Reimbursments{" +
                "reimb_id=" + reimb_id +
                ", amount=" + amount +
                ", date_submitted='" + date_submitted + '\'' +
                ", date_resolved='" + date_resolved + '\'' +
                ", description='" + description + '\'' +
                ", receipt='" + receipt + '\'' +
                ", author=" + author +
                ", resolver=" + resolver +
                ", reimb_stat_id=" + reimb_stat_id +
                ", reimb_stat_type=" + reimb_stat_type +
                '}';
    }
}
