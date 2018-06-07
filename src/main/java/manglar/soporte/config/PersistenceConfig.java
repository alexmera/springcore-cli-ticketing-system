package manglar.soporte.config;

import java.util.concurrent.ConcurrentMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:persistence.properties")
public class PersistenceConfig {

  @Bean(destroyMethod = "close")
  public DB mapDb(@Value("${database.path}") String databasePath) {
    return DBMaker.fileDB(databasePath).make();
  }

  @Bean
  public ConcurrentMap<Long, String> tickets(DB db) {
    return db.hashMap("tickets", Serializer.LONG, Serializer.STRING).createOrOpen();
  }

}
