import java.util.Scanner;
import java.util.InputMismatchException;
/**
 * POST 상품 데이터베이스, 세일 데이터베이스, 바코드 스캔, 결제, 영수증 출력등을 관리하는 클래스
 * @author (작성자 이름)
 * @version (2026.06.11)
 */
public class POST
{
    private Sale sale; //진행중인 거래
    private Products[] productDB; //상품 데이터베이스
    private Sale[] saleDB; //세일 데이터베이스
    private int saleRecord; //완료한 거래 기록 횟수
    private Scanner scanner = new Scanner(System.in);
    
    /**
     * POST 클래스의 객체 생성자
     */
    public POST()
    {
        productDB = new Products[10];
        saleDB = new Sale[100];
        
        productDB[0] = new Beverages("880001", "콜 라",       2000);
        productDB[1] = new Beverages("880002", "사이다", 2000);
        productDB[2] = new Beverages("880003", "솔의눈", 1500);
        productDB[3] = new Beverages("880004", "밀키스",   1500);
        productDB[4] = new Beverages("880005", "봉 봉",   1500);
        
        productDB[5] = new AlcoholicDrinks("880006", "참이슬", 2000, "소주");
        productDB[6] = new AlcoholicDrinks("880007", "진 로",   2000, "소주");
        productDB[7] = new AlcoholicDrinks("880008", "카 스",   3000, "맥주");
        productDB[8] = new AlcoholicDrinks("880009", "테 라",   3000, "맥주");
        productDB[9] = new AlcoholicDrinks("880010", "칭따오", 3000, "맥주");
    }

    /**
     * 거래 시작 메소드
     */
    public void startSale()
    {
        sale = new Sale();
        System.out.println("[ 새 거래 시작 ]");
    }

    /**
     * 바코드로 상품 검색하는 메소드
     * @param  barcode  바코드
     * @return    해당하는 상품 객체
     */
    public Products findProduct(String barcode)
    {
        for(int i = 0; i < productDB.length; i++){
            if(productDB[i].getBarcode() == barcode){
                return productDB[i];
            }
        }
        return null;
    }

    /**
     * 매장의 상품들 목록을 출력하는 메소드
     */
    public void printProductList()
    {
        System.out.println("[ 상품 목록 ]");
        System.out.println("< 음료 >");
        for(int i = 0; i < productDB.length; i++){
            if(i == 5){
                System.out.println("< 주류 >");
            }
            System.out.println(productDB[i].getBarcode() + ":" + productDB[i].getName() + " " + productDB[i].getPrice() + "원");
        }
        System.out.println("------------------");
    }

    /**
     * 바코드 스캔하고 상품 추가하는 메소드
     * @param  barcode  바코드, quantity  수량
     */
    public void scanBarcode()
    {
        while (true) {
            System.out.print("바코드 입력 (완료시 '0') : ");
            String barcode = scanner.next();

            if (barcode == "0") {
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
                catch(InputMismatchException e){
                    System.out.println("숫자를 입력하세요");
                    String ex = scanner.nextLine();
               }
            }
            
            Products product = findProduct(barcode);
            if(product == null){
                System.out.println("해당 상품은 없습니다");
                return;
            }
            sale.addProduct(product, quantity);
            System.out.println("[추가]" + sale.getProductCount() + ". " + product.getName() + "x" + quantity);
        }
    }

    /**
     * 총액 계산하는 메소드
     * @return    총액
     */
    public int calculateTotal()
    {
        int total = 0;
        for(int i = 0; i < sale.getProductCount(); i++){
            total += sale.getProduct(i).calculatePrice(sale.getQuantity(i));
        }
        return total;
    }

    /**
     * 거래 종료 메소드
     */
    public void finishSale()
    {
        int total = calculateTotal();
        System.out.println("지불할 금액 : " + total + "원");
    }

    /**
     * 상품 삭제 메소드
     * @param  index  삭제할 상품의 인덱스
     */
    public void removeProduct(int index)
    {
        sale.removeProduct(index);
    }

    /**
     * 영수증 출력 메소드
     * @param  cash  현금, change  거스름돈
     */
    public void printReceipt(int cash, int change)
    {
        System.out.println("============================");
        System.out.println("           영수증");
        System.out.println("============================");
        System.out.println("상품명     단가   수량   금액");
        System.out.println("----------------------------");
        
        double totalVAT = 0;
        double totalLiquorTax = 0;
        double totalEduTax = 0;
        
        for(int i = 0; i < sale.getProductCount(); i++){
            Products product = sale.getProduct(i);
            int quantity = sale.getQuantity(i);
            int price = product.getPrice();
            Tax t = (Tax) product;
            double vat = t.calculateVAT(product.getPrice());
            double liquorTax = t.calculateLiquorTax(product.getPrice());
            double eduTax = t.calculateEduTax(product.getPrice());
            int subtotal = product.getPrice() * quantity;
            
            totalVAT += vat * quantity;
            totalLiquorTax += liquorTax * quantity;
            totalEduTax += eduTax * quantity;
            
            System.out.println(product.getName() + "      " + product.getPrice() + "   " + quantity + "   " + subtotal + "원");
        }
        System.out.println("----------------------------");
        System.out.println("총 구매액    : " + calculateTotal() + "원");
        System.out.println("부가세      : " + (int)totalVAT + "원");
        if(totalLiquorTax > 0){
            System.out.println("주세       : " + (int)totalLiquorTax + "원");
            System.out.println("교육세      : " + (int)totalEduTax + "원");
        }
        System.out.println("----------------------------");
        System.out.println("결제 금액    : " + calculateTotal() + "원");
        System.out.println("현금        : " + cash + "원");
        System.out.println("거스름돈     : " + change + "원");
        System.out.println("============================");
    }

    /**
     * 결제 메소드
     */
    public void payment()
    {
        if (sale.getProductCount() == 0) {
            System.out.println("[ 결제 취소 ]");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        double total = calculateTotal();

        while (true) {
            int cash = 0;
            while(true){
                try{
                    System.out.print("현금 입력 : ");
                    cash = scanner.nextInt();
                    if(cash <= 0){
                        System.out.println("0원 이상의 금액을 입력하세요");
                        continue;
                    }
                    break;
                }
                catch(InputMismatchException e){
                    System.out.println("숫자를 입력하세요");
                    String ex = scanner.nextLine();
                }
            }

            if (cash >= total) {
                int change = cash - (int)total;
                printReceipt(cash, change);
                saleDB[saleRecord] = sale;
                saleRecord++;
                if (change > 0) {
                    System.out.println("[ 결제 완료 ] 거스름돈 : " + change + "원");
                } else {
                    System.out.println("[ 결제 완료 ]");
                }
                return;
            } else {
                System.out.println("[ 현금이 " + (int)(total - cash) + "원 부족합니다. ]");
                System.out.println("1. 계산 전체 취소");
                System.out.println("2. 상품 일부 취소");

                int choice = 0;
                while(true){
                    try{
                        System.out.print("선택 : ");
                        choice = scanner.nextInt();
                        if(choice != 1 && choice != 2){
                            System.out.println("1또는 2를 입력하세요");
                            continue;
                        }
                        break;
                    }
                    catch(InputMismatchException e){
                        System.out.println("숫자를 입력하세요");
                        String ex = scanner.nextLine();
                    }
                }

                if (choice == 1) {
                    System.out.println("[결제 취소]");
                    return;
                } else if (choice == 2) {
                    int index = 0;
                    while(true){
                        try{
                            System.out.print("취소할 상품 번호 (1~" + sale.getProductCount() + ") : ");
                            index = scanner.nextInt() - 1;
                            if(index < 0){
                                System.out.println("올바른 번호를 입력하세요");
                                continue;
                            }
                            if(index >= sale.getProductCount()){
                                System.out.println("올바른 번호를 입력하세요");
                                continue;
                            }
                            break;
                        }
                        catch(InputMismatchException e){
                            System.out.println("올바른 번호를 입력하세요");
                            String ex = scanner.nextLine();
                        }
                    }

                    removeProduct(index);
                    total = calculateTotal();
                    System.out.println("지불할 금액 : " + total + "원");
                }
            }
        }
    }

}
