
/**
 * Beverge 음료에 대한 클래스
 *
 * @author (2023320035_윤효준, 2025320029_이인성, 2025320005_김강빈, 2025320062_이진혁)
 * @version (2026.06.11)
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
        return getOriginalPrice() * 0.1;
    }

    /**
     * (세금 포함된)금액 계산 메소드
     *
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
        return getPrice() / 1.1;
    }
}
