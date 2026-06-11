
/**
 * Tax - 세금 계산 인터페이스
 *
 * @author (2023320035_윤효준, 2025320029_이인성, 2025320005_김강빈, 2025320062_이진혁)
 * @version (2026.06.11)
 */
public interface Tax
{
    /**
     * 부가가치세 계산
     */
    public double calculateVAT(int price);

    /**
     * 주세 계산
     */
    default public double calculateLiquorTax(int price)
    {
        return 0;
    }

    /**
     * 교육세 계산
     */
    default public double calculateEduTax(int price)
    {
        return 0;
    }
}
