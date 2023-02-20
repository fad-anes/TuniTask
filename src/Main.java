import Entity.Evenement;
import Service.ServiceEvenement;
import Service.ServiceReservation;
import Util.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.Connection;
import java.util.Date;
import java.sql.Statement;
import java.util.List;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import Entity.Reservation;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class Main {

    private static java.sql.Date date;
    private static int nombrePersonnes;
    private static int evenementId;
    private static int idUsers;
    private static Evenement e;

    public static void main(String[] args) {
        DataSource ds = DataSource.getInstance();
        Connection cnx = ds.getCnx();
        ServiceEvenement serviceEvenement = new ServiceEvenement(cnx);

        if (cnx != null) {
            System.out.println("Connexion établie !");
        } else {
            System.out.println("La connexion a échoué.");
        }

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Que souhaitez-vous faire ?");
            System.out.println("1. Ajouter un événement");
            System.out.println("2. Supprimer un événement");
            System.out.println("3. Mise à jour d'un évenement");
            System.out.println("4. Afficher tous les événements");
            System.out.println("5. Afficher un événement par son identifiant");
            System.out.println("6. Ajouter une reservation");
            System.out.println("7. Supprimer une reservation");
            System.out.println("8. Mettre à jour une reservation");
            System.out.println("9. Afficher tous les reservations");
            System.out.println("9. Afficher une  reservation par son ID");



            int choix = scanner.nextInt();

            if (choix == 1) {
                // code de test de la méthode insert
                Evenement e = new Evenement();

                System.out.println("Saisir le nom de l'événement:");
                String nom = scanner.next();
                e.setNom(nom);

                System.out.println("Saisir la date de l'événement (au format yyyy-MM-dd):");
                String dateString = scanner.next();
                try {
                    Date date = format.parse(dateString);
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    e.setDate(sqlDate);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

                System.out.println("Saisir le lieu de l'événement:");
                String lieu = scanner.next();
                e.setLieu(lieu);

                List<Integer> id_users = new ArrayList<Integer>();
                System.out.println("Saisir le nombre de participants:");
                int n = scanner.nextInt();
                for (int i = 1; i <= n; i++) {
                    System.out.println("Saisir l'id du participant " + i + ":");
                    int id_user = scanner.nextInt();
                    id_users.add(id_user);
                }
                e.setId_users(id_users);

                serviceEvenement.insert(e);
            } else if (choix == 2) {
                System.out.println("Saisir l'id de l'événement à supprimer :");
                int id = scanner.nextInt();
                Evenement e = new Evenement();
                e.setId(id);
                serviceEvenement.delete(e);
            }
            if (choix == 3) {
                System.out.println("Saisir l'id de l'événement à mettre à jour :");
                int id = scanner.nextInt();
                Evenement e = serviceEvenement.readById(id);
                if (e == null) {
                    System.out.println("L'événement avec l'id " + id + " n'existe pas.");
                    continue;
                }

                System.out.println("Quelles informations souhaitez-vous modifier ?");
                System.out.println("1. Nom");
                System.out.println("2. Date");
                System.out.println("3. Lieu");
                System.out.println("4. Liste des participants");

                int choixModif = scanner.nextInt();

                switch (choixModif) {
                    case 1:
                        System.out.println("Saisir le nouveau nom de l'événement :");
                        String nouveauNom = scanner.next();
                        e.setNom(nouveauNom);
                        break;
                    case 2:
                        System.out.println("Saisir la nouvelle date de l'événement (au format yyyy-MM-dd) :");
                        String nouvelleDateStr = scanner.next();
                        try {
                            Date nouvelleDate = format.parse(nouvelleDateStr);
                            java.sql.Date sqlNouvelleDate = new java.sql.Date(nouvelleDate.getTime());
                            e.setDate(sqlNouvelleDate);
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 3:
                        System.out.println("Saisir le nouveau lieu de l'événement :");
                        String nouveauLieu = scanner.next();
                        e.setLieu(nouveauLieu);
                        break;
                    case 4:
                        List<Integer> nouveauxParticipants = new ArrayList<Integer>();
                        System.out.println("Saisir le nouveau nombre de participants :");
                        int nbNouveauxParticipants = scanner.nextInt();
                        for (int i = 1; i <= nbNouveauxParticipants; i++) {
                            System.out.println("Saisir l'id du nouveau participant " + i + " :");
                            int idNouveauParticipant = scanner.nextInt();
                            nouveauxParticipants.add(idNouveauParticipant);
                        }
                        e.setId_users(nouveauxParticipants);
                        break;
                    default:
                        System.out.println("Choix invalide !");
                        break;
                }

                serviceEvenement.update(e);
            }
            if (choix == 4) {
                List<Evenement> evenements = serviceEvenement.readAll();
                for (Evenement e : evenements) {
                    System.out.println("Id: " + e.getId());
                    System.out.println("Nom: " + e.getNom());
                    System.out.println("Date: " + e.getDate());
                    System.out.println("Lieu: " + e.getLieu());
                    System.out.println("Participants: " + e.getId_users().toString());
                    System.out.println();
                }
            }
            if (choix == 5) {
                System.out.println("Saisir l'id de l'événement à afficher :");
                int id = scanner.nextInt();
                Evenement e = serviceEvenement.readById(id);
                if (e == null) {
                    System.out.println("L'événement avec l'id " + id + " n'existe pas.");
                } else {
                    System.out.println("Id: " + e.getId());
                    System.out.println("Nom: " + e.getNom());
                    System.out.println("Date: " + e.getDate());
                    System.out.println("Lieu: " + e.getLieu());
                    System.out.println("Participants: " + e.getId_users().toString());
                }
            }
            if (choix == 6) {
                System.out.print("Entrez la date de la reservation (format yyyy-MM-dd) : ");
                String dateString = scanner.next();
                try {
                    Date date = format.parse(dateString);
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    Reservation reservation = new Reservation(sqlDate, 0, 0, 0); // créer une nouvelle reservation avec les informations saisies
                    if (reservation.getDate() == null) {
                        // Si la date n'a pas été saisie, utiliser la date actuelle comme valeur par défaut
                        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
                        reservation.setDate(currentDate);
                    }
                    System.out.print("Entrez le nombre de personnes : ");
                    int nombrePersonnes = scanner.nextInt();
                    reservation.setNombrePersonnes(nombrePersonnes);
                    System.out.print("Entrez l'identifiant de l'événement : ");
                    int evenementId = scanner.nextInt();
                    reservation.setEvenementId(evenementId);
                    System.out.print("Entrez l'identifiant de l'utilisateur : ");
                    int idUsers = scanner.nextInt();
                    reservation.setIdUsers(idUsers);
                    // Insérer la reservation dans la base de données
                    ServiceReservation reservation1 = new ServiceReservation(cnx);
                    reservation1.insert(reservation);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

            }
            if (choix == 7) {
                System.out.println("Saisir l'id de la reservation à supprimer :");
                int id = scanner.nextInt();
                Reservation r = new Reservation();
                r.setId(id);
                ServiceReservation reservation1 = new ServiceReservation(cnx);
                reservation1.delete(r);
            }
            if (choix == 8) {
                // Create an instance of the ServiceReservation class
                ServiceReservation reservation1 = new ServiceReservation(cnx);

                // Call the readById() method on the instance
                System.out.println("Saisir l'id de la reservation à mettre à jour :");
                int id = scanner.nextInt();
                Reservation r = reservation1.readById(id);

                if (r == null) {
                    System.out.println("La reservation avec l'id " + id + " n'existe pas.");
                    continue;
                }

                System.out.println("Quelles informations souhaitez-vous modifier ?");
                System.out.println("1. Date");
                System.out.println("2. Nombre de personnes");
                System.out.println("3. id de l'événement");

                int choixModif = scanner.nextInt();

                switch (choixModif) {
                    case 1:
                        System.out.println("Saisir la nouvelle date de la reservation (au format yyyy-MM-dd) :");
                        String nouvelleDateStr = scanner.next();
                        try {
                            Date nouvelleDate = format.parse(nouvelleDateStr);
                            java.sql.Date sqlNouvelleDate = new java.sql.Date(nouvelleDate.getTime());
                            r.setDate(sqlNouvelleDate);
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 2:
                        System.out.println("Saisir le nouveau nombre de personnes pour la reservation :");
                        int nouveauNbPersonnes = scanner.nextInt();
                        r.setNombrePersonnes(nouveauNbPersonnes);
                        break;

                    case 3:
                        // Code pour modifier l'événement associé à la réservation
                        System.out.println("Saisir le nouvel id de l'événement :");
                        int evenementId = scanner.nextInt();
                        Evenement evenement = new ServiceEvenement(cnx).readById(evenementId);
                        if (evenement == null) {
                            System.out.println("L'événement avec l'id " + evenementId + " n'existe pas.");
                            continue;
                        }
                        r.setEvenementId(evenementId);
                        reservation1.update(r);
                        System.out.println("La réservation a été mise à jour avec succès.");
                        break;
                    default:
                        System.out.println("Choix invalide !");
                        break;
                }

                reservation1.update(r);
            }
            if (choix == 9) {
                ServiceReservation serviceReservation = new ServiceReservation(cnx);
                List<Reservation> reservations = serviceReservation.readAll();

                for (Reservation r : reservations) {
                    System.out.println("Id: " + r.getId());
                    System.out.println("Date: " + r.getDate());
                    System.out.println("Nombre de personnes: " + r.getNombrePersonnes());
                    System.out.println("Id de l'événement: " + r.getEvenementId());
                    System.out.println("Id du participant: " + r.getIdUsers());
                    System.out.println();
                }
            }
            if (choix == 10) {
                // Create an instance of ReservationService
                ServiceReservation serviceReservation = new ServiceReservation(cnx);

                // Prompt the user to enter a reservation ID
                System.out.print("Enter reservation ID: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                // Call readById method with the ID parameter
                Reservation reservation = serviceReservation.readById(id);

                // Check if a reservation was returned
                if (reservation != null) {
                    System.out.println("Reservation found:");
                    System.out.println("ID: " + reservation.getId());
                    System.out.println("Date: " + reservation.getDate());
                    System.out.println("nombre participant: " + reservation.getNombrePersonnes());
                    System.out.println("ID evenement: " + reservation.getEvenementId());
                    System.out.println("ID participant: " + reservation.getIdUsers());
                } else {
                    System.out.println("Reservation not found.");
                }
            }


        }


        }
    }



    //scanner.close();

