package Server;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * Singelton class which reading configuration file
 */
public class Configurations {

        private String []result;
        InputStream inputStream;
        private static Configurations instance;

    /**
     * private constructor
     */
    private Configurations(){
            result = new String[3];
            try {
                result = this.getPropValues();
            }
            catch (IOException e){
                System.out.println("problem");
            }
        }
        public static Configurations get_instance(){
            if(instance == null)
                instance = new Configurations();
            return instance;

        }

    /**
     * reading configuration file and return string array
     * @return string array with threadPoolSize, mazeGeneratingAlgorithm and mazeSearchingAlgorithm
     * @throws IOException if there is a problem at reading the file
     */
    public String[] getPropValues() throws IOException {

            try {
                Properties prop = new Properties();
                String propFileName = "config.properties";

                inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

                if (inputStream != null) {
                    prop.load(inputStream);
                } else {
                    throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
                }



                String threadPoolSize =prop.getProperty("threadPoolSize");
                String mazeGeneratingAlgorithm = prop.getProperty("mazeGeneratingAlgorithm");
                String mazeSearchingAlgorithm = prop.getProperty("mazeSearchingAlgorithm");

                result[0] = threadPoolSize;
                result[1] = mazeGeneratingAlgorithm;
                result[2] = mazeSearchingAlgorithm;
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            } finally {
                inputStream.close();
            }
            return result;
        }
    }

