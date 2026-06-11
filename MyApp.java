
/**
 * MyApp 클래스
 *
 * @author (작성자 이름)
 * @version (2026.06.11)
 */
public class MyApp
{
    public static void main(String[] args) {
        POST post = new POST(); //POST객체 생성
        
        post.printProductList(); //매장 내 상품 목록들 출력
        post.startSale(); //거래 시작
        
        post.scanBarcode(); //
        post.finishSale(); //거래 종료
        post.payment(); //결제
    }
}
