
/**
 * 주류에 대한 클래스
 *
 * @author (작성자 이름)
 * @version (버전 번호 또는 작성한 날짜)
 */
public class AlcoholicDrinks extends Products implements Tax
{   
    /**
     * AlcoholicDrinks 클래스의 객체 생성자
     */
    public AlcoholicDrinks(String barcode, String name, int price)
    {
        super(barcode, name, price);
    }

    /**
     * 주세 계산 메소드
     * @param  price  (세금 포함)금액
     * @return    주세
     */
    public double calculateLiquorTax(int price)
    {
        double originalPrice = price / 2.032;
        return originalPrice * 0.72;
    }

    /**
     * 교육세 계산 메소드
     * @param  price  (세금 포함)금액
     * @return    교육세
     */
    public double calculateEduTax(int price)
    {
        double originalPrice = price / 2.032;
        return originalPrice * 0.72 * 0.3;
    }

    /**
     * 예제 메소드 - 이 주석을 사용자에 맞게 바꾸십시오
     * @param  price  (세금 포함)금액
     * @return    부가가치세
     */
    public double calculateVAT(int price)
    {
        double originalPrice = price / 2.032;
        return(originalPrice + calculateLiquorTax(price) + calculateEduTax(price)) * 0.1;
    }

    /**
     * 금액 계산 메소드
     * @param  quantity  수량
     * @return    최종 금액
     */
    public double getOriginalPrice()
    {
        return getPrice() / 2.032;
    }
}