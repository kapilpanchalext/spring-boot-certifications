package config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rewards.RewardNetwork;
import rewards.internal.RewardNetworkImpl;
import rewards.internal.account.AccountRepository;
import rewards.internal.account.JdbcAccountRepository;
import rewards.internal.restaurant.JdbcRestaurantRepository;
import rewards.internal.restaurant.RestaurantRepository;
import rewards.internal.reward.JdbcRewardRepository;
import rewards.internal.reward.RewardRepository;

/**
 * TODO1-00: In this lab, you are going to exercise the following:
 * - Creating Spring configuration class
 * - Defining bean definitions within the configuration class
 * - Specifying the dependency relationships among beans
 * - Injecting dependencies through constructor injection
 * - Creating Spring application context in the test code
 *   (WITHOUT using Spring testContext framework)
 *
 * TODO1-01: Make this class a Spring configuration class
 * - Use an appropriate annotation.
 *
 * TODO1-02: Define four empty @Bean methods, one for the
 *          reward-network and three for the repositories.
 * - The names of the beans should be:
 *   - rewardNetwork
 *   - accountRepository
 *   - restaurantRepository
 *   - rewardRepository
 *
 * TODO1-03: Inject DataSource through constructor injection
 * - Each repository implementation has a DataSource
 *   property to be set, but the DataSource is defined
 *   elsewhere (TestInfrastructureConfig.java), so you
 *   will need to define a constructor for this class
 *   that accepts a DataSource parameter.
 * - As it is the only constructor, @Autowired is optional.
 *
 * TODO1-04: Implement each @Bean method to contain the code
 *          needed to instantiate its object and set its
 *          dependencies
 * - You can create beans from the following implementation classes
 *   - rewardNetwork bean from RewardNetworkImpl class
 *   - accountRepository bean from JdbcAccountRepository class
 *   - restaurantRepository bean from JdbcRestaurantRepository class
 *   - rewardRepository bean from JdbcRewardRepository class
 * - Note that return type of each bean method should be an interface
 *   not an implementation.
 */

@Configuration
public class RewardsConfig {
	private DataSource dataSource;

	// As this is the only constructor, @Autowired is not needed.
	public RewardsConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
		
	@Bean
	RewardNetwork rewardNetwork(){
		return new RewardNetworkImpl(
			accountRepository(), 
			restaurantRepository(), 
			rewardRepository());
	}
	
	@Bean
	AccountRepository accountRepository(){
		JdbcAccountRepository repository = new JdbcAccountRepository();
		repository.setDataSource(dataSource);
		return repository;
	}
	
	@Bean
	RestaurantRepository restaurantRepository(){
		JdbcRestaurantRepository repository = new JdbcRestaurantRepository();
		repository.setDataSource(dataSource);
		return repository;
	}
	
	@Bean
	RewardRepository rewardRepository(){
		JdbcRewardRepository repository = new JdbcRewardRepository();
		repository.setDataSource(dataSource);
		return repository;
	}
}
