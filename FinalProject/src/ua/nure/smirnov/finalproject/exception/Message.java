package ua.nure.smirnov.finalproject.exception;

public class Message {

	public static final String LOGIN_PASSWORD_EMPTY = "Login or password cannot be empty";
	public static final String DUBLICATE_DATA = "Пользователь с такими данными уже существует";
	public static final String LOGIN_PASSWORD_INCORRECT = "Login or password entered incorrectly";
	public static final String USER_IS_BLOCKED = "Sorry, but current user is blocked";
	public static final String USER_NOT_FOUND = "User isn't found";
	public static final String USER_NOT_REGISTER = "You must register";
	public static final String MANAGER_TAKE_ORDER = "Sorry, but manager cannot order car!";
	public static final String ADMIN_TAKE_ORDER = "Sorry, but admin cannot order car!";
	public static final String MANAGER_DELETE_PAID_ORDER = "You cannot delete paid order!";
	public static final String MANAGER_DELETE_ORDER_WITH_DAMAGE = "You cannot order, because damage bill isn't paid";
	public static final String MANAGER_ACCEPT_PAID_ORDER_PAY = "This order was paid!";
	public static final String MANAGER_GET_ORDER_FOR_DAMAGE = "This order isn't paid!";
	public static final String ORDER_HAVE_DAMAGE = "This order has already had a damage payment!";
	public static final String MANAGER_HAVE_NOT_ORDERS = "This order has already had a damage payment!";
	public static final String ORDER_DID_NOT_PICK = "You don't pick order!";
	public static final String USER_DID_NOT_PICK = "Вы не выбрали пользователя";
	public static final String FIELD_IS_EMPTY = "Please, enter a data";
	public static final String IS_NOT_ADMIN = "Вы не являетесь админом!";
	public static final String FELDS_IS_EMPTY = "Вы не заполнили все поля!";
	public static final String MANAGERS_IS_DISMISSED = "Вас уволили. Вы больше не имеете доступа!";
	public static final String CLIENT_HAS_ORDER = "Вы уже заказали авто!";
	public static final String PASSWORD_IS_SMALL = "Пароль должен состоять минимум из 6 символов!";
	public static final String MAILL_DOESNT_EXIST = "Такая почта не существует!";
	public static final String NAME_INCORECT = "Имена должны быть: в русском варианте, в английском варианте!";
	public static final String SURNAME_INCORECT = "Фамилии должны быть: в русском, английском, украинском вариантах!";
	public static final String CANNOT_DELETE_CAR = "Вы не можете удалить даннную машину. Она используется!";
	public static final String CART_DONT_CORRECT = "Вы не правильно ввели данные вашей карты!";
	public static final String MISTACE_DB = "Упс, что-то пошло не так! Пожалуйста повторите операцию позже!";

}
