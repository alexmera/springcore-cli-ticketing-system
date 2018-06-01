package manglar.soporte.config;

import java.util.concurrent.ConcurrentMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfig {

  @Bean(destroyMethod = "close")
  public DB mapDb() {
    return DBMaker.fileDB("tickets.db").make();
  }

  @Bean
  public ConcurrentMap<Long, String> tickets(DB db) {
    return db.hashMap("tickets", Serializer.LONG, Serializer.STRING).createOrOpen();
  }

}
