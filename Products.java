
/**
 * Products 음료와 주류의 공통 속성과 메소드를 정의한 추상클래스
 *
 * @author (2023320035_윤효준, )
 * @version (2026.06.11)
 */
public abstract class Products
{
    private String barcode; //상품 바코드
    private String name; //상품 이름
    private int price; //(세금포함)상품 금액

    /**
     * Products 클래스의 객체 생성자
     */
    public Products(String barcode, String name, int price)
    {
        this.barcode = barcode;
        this.name = name;
        this.price = price;
    }

    /**
     * 바코드 정보를 반환하는 메소드
     */
    public String getBarcode()
    {
        return barcode;
    }

    /**
     * 상품 이름 정보를 반환하는 메소드
     */
    public String getName()
    {
        return name;
    }

    /**
     * 상품 금액 정보를 반환하는 메소드
     */
    public int getPrice()
    {
        return price;
    }

    /**
     * 금액 계산 메소드
     * @param  quantity:수량
     */
    public int calculatePrice(int quantity)
    {
        return price * quantity;
    }
    
    /**
     * 상품의 원가 구하는 메소드
     */
    public abstract double getOriginalPrice();
}
