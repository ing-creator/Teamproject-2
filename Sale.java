
/**
 * Sale 거래 정보 관리하는 클래스
 *
 * @author (작성자 이름)
 * @version (버전 번호 또는 작성한 날짜)
 */
public class Sale
{
    private Products[] productList; //구매한 상품 목록
    private int count; //각 상품의 개수
    private int[] quantityList; //현재 담긴 상품의 수량

    /**
     * Sale 클래스의 객체 생성자
     */
    public Sale()
    {
        productList = new Products[100];
        quantityList = new int[100];
        count = 0;
    }

    /**
     * 상품 추가 메소드
     * @param  product  추가할 상품, quantity  수량
     */
    public void addProduct(Products product, int quantity)
    {
        productList[count] = product;
        quantityList[count] = quantity;
        count++;
    }

    /**
     * 상품 삭제 메소드
     * @param  index  삭제할 상품의 인덱스
     */
    public void removeProduct(int index)
    {
        for (int i = index; i < count - 1; i++){
            productList[i] = productList[i + 1];
            quantityList[i] = quantityList[i + 1];
        }
        count--;
    }

    /**
     * 현재 담긴 상품의 개수 반환하는 메소드
     */
    public int getProductCount()
    {
        return count;
    }

    /**
     * 인덱스에 해당하는 상품 반환하는 메소드
     * @param  index  상품 인덱스
     */
    public Products getProduct(int index)
    {
        return productList[index];
    }

    /**
     * 인덱스에 해당하는 수량 반환하는 메소드
     * @param  index  상품 인덱스
     */
    public int getQuantity(int index)
    {
        return quantityList[index];
    }
}