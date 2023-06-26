import java.nio.file.Files;
import java.nio.file.Paths;

public class CalculatorClassloader extends ClassLoader {
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.equals("Calculator")) {
            return findClass(name);
        }
        return super.loadClass(name);
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        if (name.equals("Calculator")) {
            try {
                String cwd = System.getProperty("user.dir");
                byte[] bytes = Files.readAllBytes(Paths.get(cwd, "/bin/Calculator.class"));
                return defineClass(name, bytes, 0, bytes.length);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return super.findClass(name);
    }
    
}
