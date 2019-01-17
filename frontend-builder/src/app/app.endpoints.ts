import {Injectable} from '@angular/core';
import {environment} from '../environments/environment';

@Injectable()
export class AppEndpoints {
  host = environment.host;
  paths = {
    root: () => `/`,
    buildings: () => `${this.host}/buildings`,
    building: id => `${this.host}/buildings/${id}`,
    floors: (buildingId) => `${this.host}/buildings/${buildingId}/floors`,
    floor: (buildingId, floorId) => `${this.host}/buildings/${buildingId}/floors/${floorId}`,
    rooms: (buildingId, floorId) => `${this.host}/buildings/${buildingId}/floors/${floorId}/rooms`,
    room: (buildingId, floorId, roomId) => `${this.host}/buildings/${buildingId}/floors/${floorId}/rooms/${roomId}`,
  };
  go() {
    return this.paths;
  }
}
