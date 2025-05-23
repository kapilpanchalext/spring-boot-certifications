package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import rewards.RewardNetwork;
import rewards.internal.RewardNetworkImpl;
import rewards.internal.account.AccountRepository;
import rewards.internal.account.JdbcAccountRepository;
import rewards.internal.restaurant.JdbcRestaurantRepository;
import rewards.internal.restaurant.RestaurantRepository;
import rewards.internal.reward.JdbcRewardRepository;
import rewards.internal.reward.RewardRepository;
import javax.sql.DataSource;

//	TODO-03: Add an annotation to enable Spring transaction
@Configuration
@EnableTransactionManagement
public class RewardsConfig {

	@Autowired
	DataSource dataSource;
		
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
