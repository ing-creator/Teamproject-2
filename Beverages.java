
/**
 * Beverge 음료에 대한 클래스
 *
 * @author (작성자 이름)
 * @version (버전 번호 또는 작성한 날짜)
 */
public class Beverages extends Products implements Tax
{
    /**
     * Beverge 클래스의 객체 생성자
     */
    public Beverages(String barcode, String name, int price)
    {
        super(barcode, name, price);
    }

    /**
     * 부가가치세 계산
     * @param  price  (세금 포함)금액
     * @return    부가가치세
     */
    public double calculateVAT(int price)
    {
        return price - (price / 1.1);
    }

    /**
     * 금액 계산 메소드
     *
     * @param  quantity  수량
     * @return    최종 금액
     */
    public double calculatePrice(int quantity)
    {
        return getPrice() * quantity;
    }
}