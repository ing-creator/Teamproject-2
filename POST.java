import java.util.Scanner;
/**
 * POST 상품 데이터베이스, 세일 데이터베이스, 바코드 스캔, 결제, 영수증 출력등을 관리하는 클래스
 * @author (작성자 이름)
 * @version (버전 번호 또는 작성한 날짜)
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
        productDB[0] = new Beverages("880001", "콜라",       2000);
        productDB[1] = new Beverages("880002", "사이다", 2000);
        productDB[2] = new Beverages("880003", "오렌지주스", 1500);
        productDB[3] = new Beverages("880004", "포도주스",   1500);
        productDB[4] = new Beverages("880005", "사과주스",   1500);

        productDB[5] = new AlcoholicDrinks("880006", "참이슬", 2000);
        productDB[6] = new AlcoholicDrinks("880007", "진로",   2000);
        productDB[7] = new AlcoholicDrinks("880008", "카스",   3000);
        productDB[8] = new AlcoholicDrinks("880009", "테라",   3000);
        productDB[9] = new AlcoholicDrinks("880010", "클라우드", 3000);

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
            if(productDB[i].getBarcode().equals(barcode)){
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
        for(int i = 0; i < productDB.length; i++){
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
    public double calculateTotal()
    {
        double total = 0;
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
        double total = calculateTotal();
        System.out.println("지불할 금액 : " + (int)total + "원");
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
        System.out.println("========== 영수증 ==========");
        double totalTax = 0;
        for(int i = 0; i < sale.getProductCount(); i++){
            Products product = sale.getProduct(i);
            int quantity = sale.getQuantity(i);
            int price = product.getPrice();
            Tax t = (Tax) product;
            double vat = t.calculateVAT(price);
            double liquorTax = t.calculateLiquorTax(price);
            double eduTax = t.calculateEduTax(price);
            double subtotal = price * quantity;
            totalTax += (vat + liquorTax + eduTax) * quantity;

            System.out.println(product.getName() + "  " + quantity + "개  " + (int)subtotal + "원");

            if (liquorTax > 0) {
                System.out.println("  ㄴ 주세(72%%)        : " + (int)(liquorTax * quantity) + "원");
                System.out.println("  ㄴ 교육세(주세x30%%) : " + (int)(eduTax * quantity) + "원");
            }
            System.out.println("  ㄴ 부가가치세(10%)  : " + (int)(vat * quantity) + "원");
        }
        System.out.println("----------------------------");
        System.out.println("총 구매액    : " + (int)calculateTotal() + "원");
        System.out.println("세금 합계    : " + (int)totalTax + "원");
        System.out.println("----------------------------");
        System.out.println("결제 금액    : " + (int)calculateTotal() + "원");
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
                catch(Exception e){
                    System.out.println("숫자를 입력하세요");
                    scanner.next();
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
                    catch(Exception e){
                        System.out.println("숫자를 입력하세요");
                        scanner.next();
                    }
                }

                if (choice == 1) {
                    System.out.println("[결제 취소] 다음에 또 오세요~");
                    return;
                } else if (choice == 2) {
                    int index = 0;
                    while(true){
                        try{
                            System.out.print("취소할 상품 번호 (1~" + sale.getProductCount() + ") : ");
                            index = scanner.nextInt() - 1;
                            if(index < 0){
                                throw new Exception("올바른 번호를 입력하세요");
                            }
                            if(index >= sale.getProductCount()){
                                throw new Exception("올바른 번호를 입력하세요");
                            }
                            break;
                        }
                        catch(Exception e){
                            System.out.println("올바른 번호를 입력하세요");
                            scanner.next();
                        }
                    }

                    removeProduct(index);
                    total = calculateTotal();
                    System.out.println("지불할 금액 : " + (int)total + "원");
                }
            }
        }
    }

}