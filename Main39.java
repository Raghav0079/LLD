class CustomerNotPlusException extends RuntimeException{
    public CustomerNotPlusException(String userID){
        super("User " + userId + " is not a plus customer");
    }
}


class CourseService{
     public void accessCourse(String userId) {
        if (!hasAccess(userId)) {
            // Throwing the custom exception if the user doesn't have access
            throw new CustomerNotPlusException(userId);
        }
        // continue enrollment...
    }


    private boolean hasAccess(String userId) {
        return false;  
    }
}

