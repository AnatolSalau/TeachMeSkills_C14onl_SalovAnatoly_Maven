public class Main {
    public static void main(String[] args) {
        String vm_name = null;
        vm_name = System.getProperty("java.vm.name");
        System.out.println("Running Java vm is: " + vm_name);
    }
}
