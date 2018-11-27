import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppEndpoints} from '../app.endpoints';
import {Room} from '../room';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class RoomService {
  constructor (
    private httpClient: HttpClient,
    private appEndpoints: AppEndpoints
  ) {}

  getRooms(buildingId: number, floorId: number): Observable<Room[]> {
    return this.httpClient.get<Room[]>(this.appEndpoints.go().rooms(buildingId, floorId));
  }

  getRoom(buildingId: number, floorId: number, roomId: number): Observable<Room> {
    return this.httpClient.get<Room>(this.appEndpoints.go().room(buildingId, floorId, roomId));
  }

  createRoom(buildingId: number, floorId: number, roomData: any): Observable<Room> {
    return this.httpClient.post<Room>(this.appEndpoints.go().rooms(buildingId, floorId), roomData, {responseType: 'json'});
  }
}
