import java.util.Scanner;
/**
 * MyApp 클래스
 *
 * @author (작성자 이름)
 * @version (버전 번호 또는 작성한 날짜)
 */
public class MyApp
{
    public static void main(String[] args) {
        POST post = new POST(); //POST객체 생성
        
        post.printProductList(); //매장 내 상품 목록들 출력
        post.startSale(); //거래 시작
        
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.print("바코드 입력 (완료시 '0') : ");
            String barcode = scanner.next();
            
            if (barcode.equalsIgnoreCase("0")) {
                break;
            }
            
            int quantity = 0;
            while(true){
                try{
                    System.out.print("수량 : ");
                    quantity = scanner.nextInt();
                    if(quantity <= 0){
                        System.out.println("수량은 1 이상 입니다.");
                        continue;
                    }
                    break;
                }
                catch(Exception e){
                    System.out.println("숫자를 입력하세요");
                    scanner.next();
                }
            }
            
            post.scanBarcode(barcode, quantity); //바코드 스캔 및 상품 추가
        }
        
        post.finishSale(); //거래 종료
        post.payment(); //결제
    }
}