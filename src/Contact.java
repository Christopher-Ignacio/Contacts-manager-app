public class Contact {
    private String name;
    private String number;

    public Contact(String name, String number){
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormattedNumber(){
        if(Long.parseLong(number) > 999999999L){
            return (number.substring(0, 3) + "-" + number.substring(3,6) + "-" + number.substring(6));
        } else{
            return (number.substring(0, 3) + "-" + number.substring(3));
        }

    }
}
