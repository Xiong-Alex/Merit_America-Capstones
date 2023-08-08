package com.techelevator.dao;

import com.techelevator.model.HoursOfOperation;
import com.techelevator.model.Restaurant;
import com.techelevator.model.RestaurantApproval;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcRestaurantDao implements RestaurantDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcRestaurantDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Restaurant> findAll() {
        List<Restaurant> restaurants = new ArrayList<>();
        String sql = "SELECT * FROM restaurant";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            Restaurant restaurant = mapRowToRestaurant(results);
            restaurant.setHoursOfOperation(getHoursOfOperations(restaurant.getRestaurantId()));
            restaurants.add(restaurant);
        }

        return restaurants;
    }

    @Override
    public List<Restaurant> findBy(String city, String zip, String type) {
        List<Restaurant> restaurants = new ArrayList<>();
        String sql = "SELECT * FROM restaurant " +
                "WHERE city ILIKE ? AND zip ILIKE ? AND type ILIKE ?;";

        String cityParam = "%" + city + "%";
        String zipParam = "%" + zip + "%";
        String typeParam = "%" + type + "%";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cityParam, zipParam, typeParam);

        while (results.next()) {
            Restaurant restaurant = mapRowToRestaurant(results);
            restaurants.add(restaurant);
        }

        return restaurants;
    }

    @Override
    public Restaurant findById(Long id) {
        Restaurant restaurant = null;

        String sql = "SELECT * FROM restaurant WHERE restaurant_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        if (results.next()) {
            restaurant = mapRowToRestaurant(results);
        }
        return restaurant;
    }

    @Override
    public List<Restaurant> findByInviteId(Long inviteId) {
        String sql = "SELECT  *  FROM restaurant as r " +
                "JOIN invite_restaurant as i ON i.restaurant_id = r.restaurant_id " +
                "WHERE i.invite_id = ? ; ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, inviteId);

        List<Restaurant> restaurants = new ArrayList<>();
        while (results.next()) {
            Restaurant restaurant = mapRowToRestaurant(results);
            restaurants.add(restaurant);
        }

        return restaurants;
    }

    @Override
    public boolean approve(Long restaurantId, Long inviteId, Integer guestEmailId, boolean isApprove) {
        String sql = "UPDATE restaurant_approval SET approve = ? " +
                "WHERE invite_id = ? AND email_id = ? AND restaurant_id = ?; ";
        int rows = jdbcTemplate.update(sql, isApprove, inviteId, guestEmailId, restaurantId);

        if (rows == 0) {
            sql = "INSERT INTO restaurant_approval (invite_id, email_id, restaurant_id, approve) " +
                    "VALUES (?, ?, ?, ?);";
            rows = jdbcTemplate.update(sql, inviteId, guestEmailId, restaurantId, isApprove);
        }

        return rows == 1;
    }

    @Override
    public List<RestaurantApproval> findApprovals(Long inviteId, Integer emailId) {
        String sql = "SELECT ir.restaurant_id, ra.approve FROM invite_restaurant AS ir " +
                "LEFT OUTER JOIN restaurant_approval AS ra ON ra.invite_id = ir.invite_id and ra.restaurant_id = ir.restaurant_id " +
                "WHERE ir.invite_id = ? AND (ra.email_id = ? OR ra.email_id IS NULL);";

        return jdbcTemplate.query(sql, new Object[]{inviteId, emailId}, ((rs, rowNum) -> {
            RestaurantApproval approval = new RestaurantApproval();
            approval.setInviteId(inviteId);
            approval.setEmailId(emailId);
            approval.setRestaurantId(rs.getLong("restaurant_id"));
            approval.setApprove(rs.getObject("approve", Boolean.class));
            return approval;
        }));
    }

    public int getApprovalCount(boolean approval, Long inviteId, Long restaurantId) {
        String sql = "SELECT COUNT(approve) FROM restaurant_approval " +
                "WHERE invite_id = ? AND restaurant_id = ? AND approve = ?; ";
        return jdbcTemplate.queryForObject(sql, Integer.class, inviteId, restaurantId, approval);
    }

    public List<Restaurant> findByCityAndZip(String city, String zip) {
        List<Restaurant> restaurants = new ArrayList<>();
        String sql = "SELECT * FROM restaurant WHERE city = ? AND zip = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, city, zip);

        while (results.next()) {
            Restaurant restaurant = mapRowToRestaurant(results);
            restaurants.add(restaurant);
        }

        return restaurants;
    }

    public List<Restaurant> findByCity(String city) {
        List<Restaurant> restaurants = new ArrayList<>();
        String sql = "SELECT * FROM restaurant WHERE city = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, city);

        while (results.next()) {
            Restaurant restaurant = mapRowToRestaurant(results);
            restaurants.add(restaurant);
        }

        return restaurants;
    }

    public List<Restaurant> findByZip(String zip) {
        List<Restaurant> restaurants = new ArrayList<>();
        String sql = "SELECT * FROM restaurant WHERE zip = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, zip);

        while (results.next()) {
            Restaurant restaurant = mapRowToRestaurant(results);
            restaurants.add(restaurant);
        }

        return restaurants;
    }

    private List<HoursOfOperation> getHoursOfOperations(Long restaurantId) {
        List<HoursOfOperation> hoursOfOperations = new ArrayList<>();

        String sql = "SELECT h.restaurant_id, d.name, h.open_from, h.open_to  FROM hours_of_operation as h " +
                "JOIN day_of_week as d ON d.day_id = h.day_open " +
                "WHERE h.restaurant_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, restaurantId);

        while (result.next()) {
            HoursOfOperation hrsOfOps = mapRowToHoursOfOperation(result);
            hoursOfOperations.add(hrsOfOps);
        }

        return hoursOfOperations;
    }

    private HoursOfOperation mapRowToHoursOfOperation(SqlRowSet rs) {
        HoursOfOperation hrsOfOps = new HoursOfOperation(
                rs.getString("name"),
                rs.getTime("open_from").toLocalTime(),
                rs.getTime("open_to").toLocalTime());
        return hrsOfOps;
    }

    private Restaurant mapRowToRestaurant(SqlRowSet rs) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(rs.getLong("restaurant_id"));
        restaurant.setName(rs.getString("name"));
        restaurant.setType(rs.getString("type"));
        restaurant.setStreet(rs.getString("street"));
        restaurant.setCity(rs.getString("city"));
        restaurant.setState(rs.getString("state"));
        restaurant.setZip(rs.getString("zip"));
        restaurant.setPhone(rs.getString("phone"));
        restaurant.setImgUrl(rs.getString("img_url"));

        restaurant.setHoursOfOperation(getHoursOfOperations(restaurant.getRestaurantId()));
        return restaurant;
    }
}
