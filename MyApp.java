
/**
 * MyApp 클래스
 *
 * @author (2023320035_윤효준, 2025320029_이인성, 2025320005_김강빈, 2025320062_이진혁)
 * @version (2026.06.11)
 */
public class MyApp
{
    public static void main(String[] args) {
        POST post = new POST(); //POST객체 생성
        
        post.printProductList(); //매장 내 상품 목록들 출력
        post.startSale(); //거래 시작
        
        post.scanBarcode(); //바코드 스캔
        post.finishSale(); //거래 종료
        post.payment(); //결제
    }
}
