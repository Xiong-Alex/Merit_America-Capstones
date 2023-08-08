package com.techelevator.controller;

import java.security.Principal;
import java.util.List;

import com.techelevator.model.*;
import com.techelevator.service.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import com.techelevator.service.RestaurantService;
import com.techelevator.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController()
@RequestMapping("/restaurant-tinder")
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class AppRestController {

    @Autowired
    UserService userService;
    @Autowired
    RestaurantService restaurantService;
    @Autowired
    InviteService inviteService;

    public AppRestController(UserService userService, RestaurantService restaurantService, InviteService inviteService) {
        this.userService = userService;
        this.restaurantService = restaurantService;
        this.inviteService = inviteService;
    }

    /**
     * Fetches all users in the database except the logged-in user
     * @param loggedInUser - the logged-in user
     * @return - List of User objects
     */
    @GetMapping("/users")
    public List<User> getAllUsers(Principal loggedInUser) {
        return userService.getAllUser(loggedInUser.getName());
    }

    /**
     * Fetches all restaurants in the database matching the criteria given as parameter
     * @param city - city parameter
     * @param zip - zip code parameter
     * @param type - restaurant type parameter
     * @return - List of Restaurant objects
     */
    @PreAuthorize("permitAll()")
    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants(@RequestParam(required = false) String city, 
        @RequestParam(required = false) String zip, @RequestParam(required = false) String type) {
        return restaurantService.getRestaurant(city, zip, type);
    }

    /**
     * Add an invite to the database
     * @param invite - an InviteDTO
     * @param loggedInUser - the creator of the invite
     * @return - an Invite object
     */
    @PostMapping(path = "/addinvite")
    @ResponseStatus(HttpStatus.CREATED)
    public Invite addInvite(@Valid @RequestBody InviteDTO invite, Principal loggedInUser) {
        return inviteService.addInvite(invite, loggedInUser.getName());
    }

    /**
     * Fetches all the invites created by the logged-in user
     * @param loggedeInUser - the creator of the invites
     * @return - a list of Invite objects
     */
    @GetMapping(path = "/invites")
    public List<Invite> getInvites(Principal loggedeInUser ){
        return inviteService.getInvites(loggedeInUser.getName());
    }

    /**
     * Fetches an Invite object from the database by the given invite ID.
     * @param inviteId - ID of the invite to fetch
     * @return - an Invite object matching the invite ID
     */
    @GetMapping(path = "/invite/{inviteId}")
    public Invite getInvite(@PathVariable Long inviteId){
        return inviteService.getInvite(inviteId);
    }

    /**
     * Fetches all the invitations that a guest is invited to
     * @param guestEmailId - the email id of the guest
     * @return - List of Invitations
     */
    @GetMapping(path = "/invitations-to")
    public List<Invite> getInvitationTo(@RequestParam Long guestEmailId){
        return inviteService.getInvitationsTo(guestEmailId);
    }

    @GetMapping("/invitationlinks/{inviteId}")
    public List<Guest> getInvitationLinks(HttpServletRequest request,  @PathVariable Long inviteId) {
        StringBuffer url = request.getRequestURL();
        String baseUrl = url.substring(0, url.indexOf("links")).concat("/");
        return inviteService.getInvitationLinks(baseUrl, inviteId);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/invitation/{inviteCode}")
    public Invite getInvitation(@PathVariable String inviteCode) {
        return inviteService.getInvitation(inviteCode);
    }

    /**
     * Sets thumbs-up or thumbs-dowm of a restaurant in an invitation by the invited guest.
     * Invited guest is identified by their emailId.
     * @param approve - an ApproveDTO object
     * @return - boolean if approve updated.
     */
    @PreAuthorize("permitAll()")
    @PutMapping(path = "/approve-restaurant")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean approveRestaurant(@RequestBody ApproveDTO approve) {
        return inviteService.approveRestaurant(approve);
    }

    /**
     * Fetches guest's approval status of restaurants in an invitation. Approval is true for thumbs-up,
     * false for thumbs-down and null if guest have not approved.
     * @param inviteId - the invitaiton id
     * @param emailId - email id of an invited guest
     * @return - list of RestaurantApproval object containing restaurant id, invite id, guest email id and
     * approve status
     */
    @GetMapping("invite/{inviteId}/guest-email/{emailId}/restaurant-approvals")
    public List<RestaurantApproval> getRestaurantApprovals(@PathVariable Long inviteId, @PathVariable Integer emailId) {
        return inviteService.getRestaurantApprovals(inviteId, emailId);
    }

    /**
     * Fetches number of thumbsUp and thumbsDown of restaurants of an invitation
     * @param inviteId - invitation id
     * @return - a List of RestaurantApprovalCount each containing thumbs-up and thumbs-down count of each restaurant
     */
    @GetMapping("invite/{inviteId}/restaurant-approval-counts")
    public List<RestaurantApprovalCount> getRestaurantApprovalCounts(@PathVariable Long inviteId) {
        return inviteService.getRestaurantApprovalCounts(inviteId);
    }
}
