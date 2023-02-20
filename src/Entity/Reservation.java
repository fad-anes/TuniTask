package Entity;
import java.sql.Date;

    public class Reservation {
        private int id;
        private Date date;
        private int nombrePersonnes;
        private int evenementId;
        private int idUsers;

        public Reservation() {
            // Constructeur par défaut
        }

        public Reservation(int id, Date date, int nombrePersonnes, int evenementId, int idUsers) {
            this.id = id;
            this.date = date;
            this.nombrePersonnes = nombrePersonnes;
            this.evenementId = evenementId;
            this.idUsers = idUsers;
        }
        public Reservation( Date date, int nombrePersonnes, int evenementId, int idUsers) {
            this.date = date;
            this.nombrePersonnes = nombrePersonnes;
            this.evenementId = evenementId;
            this.idUsers = idUsers;
        }


        // Getters
        public int getId() {
            return id;
        }

        public Date getDate() {
            return date;
        }

        public int getNombrePersonnes() {
            return nombrePersonnes;
        }

        public int getEvenementId() {
            return evenementId;
        }

        public int getIdUsers() {
            return idUsers;
        }

        // Setters
        public void setId(int id) {
            this.id = id;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public void setNombrePersonnes(int nombrePersonnes) {
            this.nombrePersonnes = nombrePersonnes;
        }

        public void setEvenementId(int evenementId) {
            this.evenementId = evenementId;
        }

        public void setIdUsers(int idUsers) {
            this.idUsers = idUsers;
        }

        // ToString
        @Override
        public String toString() {
            return "Reservation [id=" + id + ", date=" + date + ", nombrePersonnes=" + nombrePersonnes + ", evenementId="
                    + evenementId + ", idUsers=" + idUsers + "]";
        }

    }


