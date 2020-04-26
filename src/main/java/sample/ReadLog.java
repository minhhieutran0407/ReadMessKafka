package sample;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Collections;
import java.util.Properties;

public class ReadLog {
    private static final String TOPIC = "test";
    private static final String BOOTSTRAP_SERVER = "localhost:9092";

    private static Consumer<Integer, String> createConsumer(){
        final Properties prop = new Properties();
        prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        prop.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaConsumer");
        prop.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        //prop.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        //prop.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        prop.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        //Create the consumer using properties
        final Consumer<Integer, String> consumer = new KafkaConsumer<Integer, String>(prop);

        //Subcribe to the topic
        consumer.subscribe(Collections.singleton(TOPIC));

        return consumer;
    }

    static void runConsumer() throws InterruptedException{
        final Consumer<Integer, String> consumer = createConsumer();

        //final int giveup = 10000;
        //int noRecordsCount = 0;

        while(true){
            ConsumerRecords<Integer, String> consumerRecords = consumer.poll(1000);

            /*
            if (consumerRecords.count() == 0){
                noRecordsCount++;
                if (noRecordsCount > giveup) break;
                else continue;
            }
            */

            consumerRecords.forEach(record -> {
                /*
                System.out.printf("Consumer Record:(%d, %s, %d, %d)\n",
                        record.key(), record.value(),
                        record.partition(), record.offset());
                 */

                String data = record.value();
                JSONParser parser = new JSONParser();
                try {

                    //Convert to JSONObject
                    JSONObject object = (JSONObject) parser.parse(data);

                    //get Object 'data' Key and Value
                    JSONObject struct = (JSONObject) object.get("data");

                    //Get Key array
                    JSONArray keyArray = (JSONArray) struct.get("key");
                    /*
                    System.out.println("Key: ");
                    //take the element of key array
                    for (int i = 0; i < keyArray.size(); i++){
                        System.out.println(keyArray.get(i));
                    }

                     */

                    //Get Key array
                    JSONArray valueArray = (JSONArray) struct.get("value");

                    System.out.println("Value: ");
                    //take the element of key array
                    for (int i = 0; i < valueArray.size(); i++){
                        System.out.println(valueArray.get(i));
                    }

                    Data data1 = new Data();
                    data1.setDate((String) valueArray.get(0));
                    data1.setTime((String) valueArray.get(1));
                    data1.setTag((String) valueArray.get(2));
                    data1.setMess((String) valueArray.get(3));
                    Controller controller = new Controller();
                    controller.dataList.add(data1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });

            //consumer.commitAsync();
        }

        //consumer.close();
        //System.out.println("DONE");
    }
}
