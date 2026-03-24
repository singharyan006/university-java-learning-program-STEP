public class InvalidBookingValidator {

    public void validateReservationInput(Reservation reservation, RoomInventory inventory) throws InvalidBookingException {
        if (reservation == null) {
            throw new InvalidBookingException("Reservation request cannot be null.");
        }

        if (reservation.getGuestName() == null || reservation.getGuestName().trim().isEmpty()) {
            throw new InvalidBookingException("Guest name is required.");
        }

        if (reservation.getRoomType() == null || reservation.getRoomType().trim().isEmpty()) {
            throw new InvalidBookingException("Room type is required.");
        }

        if (!inventory.hasRoomType(reservation.getRoomType())) {
            throw new InvalidBookingException("Invalid room type: " + reservation.getRoomType());
        }
    }

    public void validateBeforeAllocation(String roomType, RoomInventory inventory) throws InvalidBookingException {
        if (inventory.getAvailableRooms(roomType) <= 0) {
            throw new InvalidBookingException("Room type unavailable: " + roomType);
        }
    }
}
