
/**
 * Tax - 세금 계산 인터페이스
 *
 * @author (작성자 이름)
 * @version (버전 번호나 날짜)
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