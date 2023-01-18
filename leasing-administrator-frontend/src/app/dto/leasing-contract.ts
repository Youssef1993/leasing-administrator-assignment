import {Vehicle} from "./vehicle";
import {Customer} from "./customer";

export class LeasingContract {
  vehicle: Vehicle;
  customer: Customer;
  contractNumber: string;
  monthlyRate: number;
}
