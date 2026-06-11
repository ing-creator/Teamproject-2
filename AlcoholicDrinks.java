
/**
 * 주류에 대한 클래스
 *
 * @author (작성자 이름)
 * @version (2026.06.11)
 */
public class AlcoholicDrinks extends Products implements Tax
{   
    private String alcoholType; //주류 타입(소주, 맥주)
    
    /**
     * AlcoholicDrinks 클래스의 객체 생성자
     */
    public AlcoholicDrinks(String barcode, String name, int price, String alcoholType)
    {
        super(barcode, name, price);
        this.alcoholType = alcoholType;
    }

    /**
     * 주세 계산 메소드
     * @param  price  (세금 포함)금액
     * @return    주세
     */
    public double calculateLiquorTax(int price)
    {
        if(alcoholType == "맥주"){
            return 885.7 * 0.5;
        }
        else{
            return getOriginalPrice() * 0.72;
        }
    }

    /**
     * 교육세 계산 메소드
     * @param  price  (세금 포함)금액
     * @return    교육세
     */
    public double calculateEduTax(int price)
    {
        return calculateLiquorTax(price) * 0.3;
    }

    /**
     * 부가가치세 계산 메소드
     * @param  price  (세금 포함)금액
     * @return    부가가치세
     */
    public double calculateVAT(int price)
    {
        return (getOriginalPrice() + calculateLiquorTax(price) + calculateEduTax(price)) * 0.1;
    }

    /**
     * 금액 계산 메소드
     * @param  quantity  수량
     * @return    최종 금액
     */
    public int calculatePrice(int quantity)
    {
        return getPrice() * quantity;
    }

    /**
     * 원가 구하는 메소드
     * @return    원가
     */
    public double getOriginalPrice()
    {
        if(alcoholType == "맥주"){
            double liquorTax = 885.7 * 0.5;
            double eduTax = liquorTax * 0.3;
            double originalPrice = (getPrice() - liquorTax - eduTax) / 1.1;
            return originalPrice;
        }
        else{
            return getPrice() / 2.1296;
        }
    }
}
